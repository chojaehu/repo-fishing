const seatMapElement = document.getElementById('seatMap');
        const numberOfSeats = 16;
        const centerX = seatMapElement.offsetWidth / 2;
        const centerY = seatMapElement.offsetHeight / 2;
        const semiMajorAxis = 350; // 타원의 장축 반지름 (가로)
        const semiMinorAxis = 100; // 타원의 단축 반지름 (세로)
        const angleIncrement = (Math.PI * 2) / numberOfSeats; // 좌석 간의 각도 간격

        let selectedSeat = null; // 현재 선택된 좌석
        let selectedSeatNumber = null; // 선택된 좌석 번호
        let revSeat = null; // revSeat 변수 추가

        for (let i = 0; i < numberOfSeats; i++) {
            const angle = i * angleIncrement; // 현재 좌석의 각도 계산
            const x = centerX + Math.cos(angle) * semiMajorAxis; // x 좌표 계산
            const y = centerY + Math.sin(angle) * semiMinorAxis; // y 좌표 계산

            const seatElement = document.createElement('div');
            seatElement.classList.add('seat');
            seatElement.textContent = i + 1;
            seatElement.style.left = (x - 20) + 'px'; // 좌석의 너비 절반을 뺌
            seatElement.style.top = (y - 20) + 'px'; // 좌석의 높이 절반을 뺌

            seatElement.addEventListener('click', function() {
                if (selectedSeat) {
                    selectedSeat.classList.remove('selected'); // 기존 선택된 좌석 해제
                }
                seatElement.classList.add('selected'); // 새로운 좌석 선택
                selectedSeat = seatElement; // 선택된 좌석 갱신

                // 선택된 좌석 번호 업데이트
                selectedSeatNumber = parseInt(seatElement.textContent); // 좌석 번호를 int로 변환
                const revSeatElement = document.getElementById('revSeat');
                revSeatElement.textContent = '좌석번호 : '+  selectedSeatNumber;

                // 숨겨진 입력 필드 업데이트
                const revSeatInputElement = document.getElementById('revSeatInput');
                revSeatInputElement.value = selectedSeatNumber;
            });
            seatMapElement.appendChild(seatElement);
        }
        
        let goUrlCheckout = "/checkout";
        
        let form = document.querySelector("form[name=formList]");
        document.getElementById("Checkoutbtn").onclick = function(){
            form.action = goUrlCheckout;
            form.submit();
        }