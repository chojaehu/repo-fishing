let selectedFile = null;

document.getElementById('uploadFiles').addEventListener('change', function(event) {
    let files = Array.from(event.target.files); // 선택된 모든 파일
    let imageContainerWrapper = document.getElementById('imageContainerWrapper');
    
    // 이전에 추가된 모든 이미지 컨테이너 제거
    imageContainerWrapper.innerHTML = '';

    files.forEach((file, index) => {
        if (file && file.type.startsWith('image/')) {
            let reader = new FileReader();

            reader.onload = function(e) {
                // 이미지 컨테이너 생성
                let imageContainer = document.createElement('div');
                imageContainer.className = 'image-container';

                // 이미지 요소 생성
                let imagePreview = document.createElement('img');
                imagePreview.src = e.target.result;
                imagePreview.alt = file.name;

                // 닫기 버튼 생성
                let closeButton = document.createElement('button');
                closeButton.className = 'close-button';
                closeButton.innerHTML = '&times;';

                // 파일명 요소 생성
                let fileName = document.createElement('div');
                fileName.className = 'file-name';
                fileName.textContent = file.name;

                // 닫기 버튼 클릭 이벤트 리스너
                closeButton.addEventListener('click', function() {
                    // 이미지 컨테이너를 래퍼에서 제거
                    imageContainerWrapper.removeChild(imageContainer);
                    // 선택된 파일 목록에서 해당 파일 제거
                    files = files.filter(selectedFile => selectedFile !== file);
                    // 파일 입력 요소 초기화 및 재설정
                    const dataTransfer = new DataTransfer();
                    files.forEach(file => dataTransfer.items.add(file));
                    document.getElementById('uploadFiles').files = dataTransfer.files;
                });

                // 이미지 컨테이너에 이미지, 닫기 버튼, 파일명 추가
                imageContainer.appendChild(imagePreview);
                imageContainer.appendChild(closeButton);
                imageContainer.appendChild(fileName);

                // 이미지 컨테이너를 래퍼에 추가
                imageContainerWrapper.appendChild(imageContainer);
            };

            reader.readAsDataURL(file); // 파일을 DataURL로 읽음
        } else {
            alert("이미지 파일을 선택해 주세요");
        }
    });
});