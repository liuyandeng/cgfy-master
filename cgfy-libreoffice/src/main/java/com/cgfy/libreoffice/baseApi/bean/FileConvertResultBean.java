package com.cgfy.libreoffice.baseApi.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileConvertResultBean {

  private String status;

  private String targetFileName;

}
