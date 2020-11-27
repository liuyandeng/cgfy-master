package com.cgfy.user.base.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.cgfy.user.base.bean.BaseSelectField;

/**
 * JSON配置
 */
@Configuration
public class JacksonConfig {

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer objectMapperBuilder() {
		return new Jackson2ObjectMapperBuilderCustomizer() {

			@Override
			public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
				jacksonObjectMapperBuilder
						.filters(new SimpleFilterProvider().addFilter("SelectFieldFilter", new SelectFieldFilter()));

			}

		};
	}

	public static class SelectFieldFilter extends SimpleBeanPropertyFilter {

		@Override
		public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider,
				PropertyWriter writer) throws Exception {

			if (pojo instanceof BaseSelectField) {
				BaseSelectField selectFieldBean = BaseSelectField.class.cast(pojo);
				if (selectFieldBean.getSelectFieldList() != null && selectFieldBean.getSelectFieldList().size() != 0
						&& !selectFieldBean.getSelectFieldList().contains(writer.getName())) {
					return;
				}
			}

			super.serializeAsField(pojo, jgen, provider, writer);
		}
	}
}