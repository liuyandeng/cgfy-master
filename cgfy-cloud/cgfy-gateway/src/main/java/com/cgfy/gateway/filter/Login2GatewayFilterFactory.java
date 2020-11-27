package com.cgfy.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;


@Slf4j
public class Login2GatewayFilterFactory extends AbstractGatewayFilterFactory<Login2GatewayFilterFactory.Config> {


	private final static String REQUEST_RECORDER_LOG_BUFFER = "RequestRecorderGatewayFilter.request_recorder_log_buffer";



	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	public Login2GatewayFilterFactory() {
		super(Config.class);
		log.info("Loaded GatewayFilterFactory [Authorize]");
	}

	@Override
	public List<String> shortcutFieldOrder() {
		return Arrays.asList("enabled");
	}

	@Override
	public GatewayFilter apply(Config config) {
		return new GatewayFilter() {
			@Override
			public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
				if (!config.isEnabled()) {
					return chain.filter(exchange);
				}
				
				RecorderServerHttpResponseDecorator response = new RecorderServerHttpResponseDecorator(exchange.getResponse());
				
				ServerWebExchange ex = exchange.mutate()
		                .request(new RecorderServerHttpRequestDecorator(exchange.getRequest()))
		                .response(response)
		                .build();
				
//				response.subscribe(
//		                Mono.defer(() -> recorderRouteRequest(ex)).then(
//		                        Mono.defer(() -> recorderResponse(ex))
//		                )
//		        );
				
				// return recorderOriginalRequest(ex).then(Mono.defer(() -> recorderResponse(ex)));
				return recorderOriginalRequest(ex).then(chain.filter(ex)).then(Mono.defer(() -> recorderResponse(ex)));
			}
		};
	}
	
    private Mono<Void> recorderOriginalRequest(ServerWebExchange exchange) {
        StringBuffer logBuffer = new StringBuffer("\n------------开始时间 ").append(System.currentTimeMillis()).append("------------");
        exchange.getAttributes().put(REQUEST_RECORDER_LOG_BUFFER, logBuffer);

        ServerHttpRequest request = exchange.getRequest();
        return recorderRequest(request, request.getURI(), logBuffer.append("\n原始请求：\n"));
    }
    
	
    private Mono<Void> recorderRouteRequest(ServerWebExchange exchange) {
        URI requestUrl = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        StringBuffer logBuffer = exchange.getAttribute(REQUEST_RECORDER_LOG_BUFFER);

        return recorderRequest(exchange.getRequest(), requestUrl, logBuffer.append("代理请求：\n"));
    }
    
    private Mono<Void> recorderRequest(ServerHttpRequest request, URI uri, StringBuffer logBuffer) {
        if (uri == null) {
            uri = request.getURI();
        }
        HttpMethod method = request.getMethod();
        HttpHeaders headers = request.getHeaders();

        logBuffer.append(method.toString()).append(' ').append(uri.toString()).append('\n');

        logBuffer.append("------------请求头------------\n");
        
        headers.forEach((name, values) -> {
            values.forEach(value -> {
                logBuffer.append(name).append(":").append(value).append('\n');
            });
        });

        Charset bodyCharset = null;
        if (hasBody(method)) {
            long length = headers.getContentLength();
            if (length <= 0) {
                logBuffer.append("------------无body------------\n");
            } else {
                logBuffer.append("------------body 长度:").append(length).append(" contentType:");
                MediaType contentType = headers.getContentType();
                if (contentType == null) {
                    logBuffer.append("null，不记录body------------\n");
                } else if (!shouldRecordBody(contentType)) {
                    logBuffer.append(contentType.toString()).append("，不记录body------------\n");
                } else {
                    bodyCharset = getMediaTypeCharset(contentType);
                    logBuffer.append(contentType.toString()).append("------------\n");
                }
            }
        }


        if (bodyCharset != null) {
            return doRecordBody(logBuffer, request.getBody(), bodyCharset)
                    .then(Mono.defer(() -> {
                        logBuffer.append("\n------------ end ------------\n\n");
                        return Mono.empty();
                    }));
        } else {
            logBuffer.append("------------ end ------------\n\n");
            return Mono.empty();
        }
    }
    
    private Mono<Void> recorderResponse(ServerWebExchange exchange) {
        RecorderServerHttpResponseDecorator response = (RecorderServerHttpResponseDecorator)exchange.getResponse();
        StringBuffer logBuffer = exchange.getAttribute(REQUEST_RECORDER_LOG_BUFFER);

        HttpStatus code = response.getStatusCode();
        logBuffer.append("响应：").append(code.value()).append(" ").append(code.getReasonPhrase()).append('\n');

        HttpHeaders headers = response.getHeaders();
        logBuffer.append("------------响应头------------\n");
        headers.forEach((name, values) -> {
            values.forEach(value -> {
                logBuffer.append(name).append(":").append(value).append('\n');
            });
        });

//        Charset bodyCharset = null;
//        MediaType contentType = headers.getContentType();
//        if (contentType == null) {
//            logBuffer.append("------------ contentType = null，不记录body------------\n");
//        } else if (!shouldRecordBody(contentType)) {
//            logBuffer.append("------------不记录body------------\n");
//        } else {
//            bodyCharset = getMediaTypeCharset(contentType);
//            logBuffer.append("------------body------------\n");
//        }
//
//        if (bodyCharset != null) {
//            return doRecordBody(logBuffer, response.copy(), bodyCharset)
//                    .then(Mono.defer(() -> writeLog(exchange)));
//        } else {
//            return writeLog(exchange);
//        }
        return writeLog(exchange);
    }

    
    private Mono<Void> doRecordBody(StringBuffer logBuffer, Flux<DataBuffer> body, Charset charset) {
        return DataBufferUtils.join(body).doOnNext(buffer -> {
                CharBuffer charBuffer = charset.decode(buffer.asByteBuffer());
                logBuffer.append(charBuffer.toString());
                DataBufferUtils.release(buffer);
        }).then();
    }

    private boolean hasBody(HttpMethod method) {
        //只记录这3种谓词的body
        if (method == HttpMethod.POST || method == HttpMethod.PUT || method == HttpMethod.PATCH)
            return true;

        return false;
    }
    
    //记录简单的常见的文本类型的request的body和response的body
    private boolean shouldRecordBody(MediaType contentType) {
        String type = contentType.getType();
        String subType = contentType.getSubtype();

        if ("application".equals(type)) {
            return "json".equals(subType) || "x-www-form-urlencoded".equals(subType) || "xml".equals(subType) || "atom+xml".equals(subType) || "rss+xml".equals(subType);
        } else if ("text".equals(type)) {
            return true;
        }

        //暂时不记录form
        return false;
    }
    
    private Charset getMediaTypeCharset(MediaType mediaType) {
        if (mediaType != null && mediaType.getCharset() != null) {
            return mediaType.getCharset();
        }
        else {
            return StandardCharsets.UTF_8;
        }
    }
    
    private Mono<Void> writeLog(ServerWebExchange exchange) {
        StringBuffer logBuffer = exchange.getAttribute(REQUEST_RECORDER_LOG_BUFFER);
        logBuffer.append("\n------------ end at ")
                .append(System.currentTimeMillis())
                .append("------------\n\n");

        log.info(logBuffer.toString());
        return Mono.empty();
    }
    
	public static class Config {
		// 控制是否开启认证
		private boolean enabled;

		public Config() {

		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
	}

}
