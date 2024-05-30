package com.stayc.common.fileupload;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileUpLoadService {
	@Value("${file_upload_type}")
	private String fileUploadType;
	
	@Autowired
	FileUpLoadDao dao;
	
	// 파일업로드 저장
	public int insertFileUpLoad(FileUpLoadDto dto) {
		dto.setStorage(fileUploadType);
		return dao.insertFileUpLoad(dto);
	}
	
	// 대표수정
	public int updateDefaultNy(FileUpLoadDto dto) {
		dto.setStorage(fileUploadType);
		return dao.updateDefaultNy(dto);
	};
	
	// 파일정보수정
	public int updateUuidName(FileUpLoadDto dto) {
		dto.setStorage(fileUploadType);
		return dao.updateUuidName(dto);
	};
	
	// 파일업로드 삭제
	public int deleteFileUpLoad(FileUpLoadDto dto) {
		return dao.deleteFileUpLoad(dto);
	};	
	
	// 키조회
	public List<FileUpLoadDto> selectListUuidName(FileUpLoadDto dto) {
		dto.setStorage(fileUploadType);
		return dao.selectListUuidName(dto);
	};
}
