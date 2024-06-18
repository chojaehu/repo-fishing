package com.stayc.infra.reservation;

import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.stayc.common.base.BaseService;
import com.stayc.common.fileupload.FileUpLoadDto;
import com.stayc.common.fileupload.FileUpLoadService;

@Service
public class reservationService {
	
	@Autowired
	AmazonS3Client amazonS3Client;
	
	@Autowired
	FileUpLoadService fileUpLoadService;
	
	@Value("${cloud.aws.s3.bucket}") private String bucket;
	
	@Autowired
	reservationDao dao;
	
//	예약리스트
	public List<reservationDto> selectList(reservationVo vo) {
		return dao.selectList(vo);
	}
	
//	예약상세 리스트
	public reservationDto selectOne(reservationDto dto) {
		return dao.selectOne(dto);
	}
	
//	결제카드정보 insert
	public int payInsert(reservationDto dto) {
		return dao.payInsert(dto);
	}
	
//	결제완료 insert
	public int insert(reservationDto dto) {
		return dao.insert(dto);
	}
	
//	어종 리스트
	public List<reservationDto> fisList(reservationDto dto) {
		return dao.fisList(dto);
	}
	
//	채집도구 리스트
	public List<reservationDto> prpList(reservationDto dto) {
		return dao.prpList(dto);
	}
	
// 	낚시터 갯수
	public int selectOneCount(reservationVo vo) {
		return dao.selectOneCount(vo);
	}
	
	// 여러파일 업로드(AWS S3)
	//========================================================================= AWS S3
	public void fileUploadsS3(MultipartFile[] multipartFiles, FileUpLoadDto dto, FileUpLoadDto fDto) throws Exception {
		String originalName = null;
		String ext;
		String uuidName;
		String pathName;
		String defaultName = dto.getDefaultNy();
		
		for(int i = 0; i < multipartFiles.length; i++) {
			if(!multipartFiles[i].isEmpty()) {
				// 파일명 예)img01.jpg
				originalName = multipartFiles[i].getOriginalFilename();
				
				// 상품이미지
				//=========================================================
				// 대표이미지 아님설정
				dto.setDefaultNy("1");
				// 순서
				dto.setSort(i);
				
				}
				
				ext = originalName.substring(originalName.lastIndexOf(".") + 1); // 파일 이름에서 확장자 추출하기	
				uuidName = UUID.randomUUID().toString()+"."+ext;
				
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(multipartFiles[i].getSize());
				metadata.setContentType(multipartFiles[i].getContentType());
				amazonS3Client.putObject(bucket, uuidName, multipartFiles[i].getInputStream(), metadata);
				pathName = amazonS3Client.getUrl(bucket, uuidName).toString();	
				
				dto.setPathName(pathName);
				dto.setPath(pathName);
				dto.setOriginalName(originalName);
				dto.setUuidName(uuidName);
				dto.setExt(ext);
				dto.setSize(multipartFiles[i].getSize());
				
				// 저장
				fileUpLoadService.insertFileUpLoad(dto);
			}
		}
		
}