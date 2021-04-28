package com.cgfy.libreoffice.baseApi.service;


import com.cgfy.libreoffice.baseApi.bean.FileConvertResultBean;

import java.io.File;
import java.io.InputStream;

public interface PreviewService {

  /**
   *
   * @param sourceFile 需要预览为文件
   * @param fileExt 文件扩展名
   */
  FileConvertResultBean convertFile2pdf(File sourceFile, String fileExt);


  /**
   *
   * @param in 文件输入流
   * @param fileExt 文件扩展名
   * @param fileName 文件名
   */
  FileConvertResultBean convertInputStream2pdf(InputStream in, String fileName, String fileExt);

}
