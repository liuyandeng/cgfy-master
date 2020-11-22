package com.cgfy.office.preview.service.impl;


import com.cgfy.office.preview.constants.Constants;
import com.cgfy.office.preview.dto.FileConvertResultDTO;
import com.cgfy.office.preview.service.PreviewService;
import com.cgfy.office.preview.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.jodconverter.DocumentConverter;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.jodconverter.office.OfficeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.InputStream;

@Service
@Slf4j
public class PreviewServiceImpl implements PreviewService {

  @Value("${jodconverter.store.path}")
  private String storePath;

  @Autowired
  private DocumentConverter documentConverter;

  @Override
  public FileConvertResultDTO convertFile2pdf(File sourceFile, String fileExt) {
    FileConvertResultDTO fileConvertResultDTO = new FileConvertResultDTO();
    try {
      fileExt = fileExt.toLowerCase();
      String fileName = FileUtil.getWithoutExtension(sourceFile.getName());
      String targetFileExt = getTargetFileExt(fileExt);
      File targetFile = new File(storePath+ FileUtil.SLASH_ONE + fileName + FileUtil.DOT + targetFileExt);
      documentConverter.convert(sourceFile).as(DefaultDocumentFormatRegistry.getFormatByExtension(fileExt))
          .to(targetFile).as(DefaultDocumentFormatRegistry.getFormatByExtension(targetFileExt)).execute();
      fileConvertResultDTO.setStatus("success");
      fileConvertResultDTO.setTargetFileName(targetFile.getName());
    } catch (OfficeException e) {
      log.error("convertFile2pdf error : " + e.getMessage(),e);
      fileConvertResultDTO.setStatus("fail");
    }
    return fileConvertResultDTO;

  }

  @Override
  public FileConvertResultDTO convertInputStream2pdf(InputStream in, String fileName, String fileExt) {
    FileConvertResultDTO fileConvertResultDTO = new FileConvertResultDTO();
    try {
      fileExt = fileExt.toLowerCase();
      fileName = FileUtil.getWithoutExtension(fileName);
      String targetFileExt = getTargetFileExt(fileExt);
      File targetFile = new File(storePath+ FileUtil.SLASH_ONE + fileName + FileUtil.DOT + targetFileExt);
      documentConverter.convert(in).as(DefaultDocumentFormatRegistry.getFormatByExtension(fileExt))
          .to(targetFile).as(DefaultDocumentFormatRegistry.getFormatByExtension(targetFileExt)).execute();
      fileConvertResultDTO.setStatus("success");
      fileConvertResultDTO.setTargetFileName(targetFile.getName());
    } catch (OfficeException e) {
      log.error("convertInputStream2pdf error : " + e.getMessage(),e);
      fileConvertResultDTO.setStatus("fail");
    }
    return fileConvertResultDTO;
  }

  /**
   * 获取想要转换的格式类型
   * @return
   */
  private String getTargetFileExt(String originFileExt){
     if(Constants.fileType2Htmls.contains(originFileExt)){
       return FileUtil.HTML;
     }
     return FileUtil.PDF;
  }

  @PostConstruct
  private void init(){
    File targetDir = new File(storePath);
    if(!targetDir.exists()){
      targetDir.mkdirs();
    }
  }
}
