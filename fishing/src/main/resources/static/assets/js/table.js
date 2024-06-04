const fishingMapElement = document.getElementById('fishingMap');
    const numberOfRows = 5;
    const seatsPerRow = 6;
    const seatWidth = 128;
    const seatHeight = 45;
    const gap = 20; // 좌석 사이 간격
    let number = numberOfRows;
    let seata = seatsPerRow;

    let fishingNumber = 1; // 좌석 번호 초기화
    let i = 1;
    let j = 1;

    let selectedFishing = null; // 선택된 좌석을 추적하기 위한 변수

    const revSeatElement = document.getElementById('revSeat');
    const revSeatInputElement = document.getElementById('revSeatInput');

    for (let row = 0; row < numberOfRows; row++) {
        j=1;
        for (let seat = 0; seat < seatsPerRow; seat++) {
            // 가운데 위치 및 모서리는 건너뜁니다.
            if (
                (row === Math.floor(numberOfRows / 2) && seat === Math.floor(seatsPerRow / 2)) ||
                (row === Math.floor(numberOfRows / 5) && seat === Math.floor(seatsPerRow / 2)) ||
                (row === Math.floor(numberOfRows / 2) && seat === Math.floor(seatsPerRow / 5)) ||
                (row === Math.floor(numberOfRows / 5) && seat === Math.floor(seatsPerRow / 5)) ||
                (row === Math.floor(numberOfRows -2) && seat === Math.floor(seatsPerRow / 5)) ||
                (row === Math.floor(numberOfRows -2) && seat === Math.floor(seatsPerRow / 2)) ||
                (row === Math.floor(numberOfRows -2) && seat === Math.floor(seatsPerRow -2)) ||
                (row === Math.floor(numberOfRows -3) && seat === Math.floor(seatsPerRow -2)) ||
                (row === Math.floor(numberOfRows -4) && seat === Math.floor(seatsPerRow -2)) ||
                (row === Math.floor(numberOfRows -4) && seat === Math.floor(seatsPerRow -4)) ||
                (row === Math.floor(numberOfRows -3) && seat === Math.floor(seatsPerRow -4)) ||
                (row === Math.floor(numberOfRows -2) && seat === Math.floor(seatsPerRow -4)) ||
                (row === Math.floor(numberOfRows - number) && seat === Math.floor(seatsPerRow - seata)) ||
                (row === Math.floor(numberOfRows -1) && seat === Math.floor(seatsPerRow -1)) ||
                (row === Math.floor(numberOfRows /number -1) && seat === Math.floor(seatsPerRow -1)) ||
                (row === Math.floor(numberOfRows -1) && seat === Math.floor(seatsPerRow /seata -1))
            ) {
                continue;
            }

            const x = seat * (seatWidth + gap);
            const y = row * (seatHeight + gap);

            const fishingElement = document.createElement('div');
            fishingElement.classList.add('fishing');
            fishingElement.textContent = fishingNumber; // 좌석 번호를 할당합니다.
            fishingElement.style.left = x + 'px';
            fishingElement.style.top = y + 'px';

            fishingElement.addEventListener('click', function() {
                // 이전에 선택된 좌석이 있다면 선택을 해제합니다.
                if (selectedFishing !== null) {
                    selectedFishing.classList.remove('selected');
                }

                // 선택된 좌석을 현재 클릭된 좌석으로 업데이트합니다.
                selectedFishing = fishingElement;
                selectedFishing.classList.add('selected');
                
                // 선택된 좌석 번호를 다루거나 보여주는 다른 동작을 수행할 수 있습니다.
                const selectedSeatNumber = parseInt(fishingElement.textContent); // 좌석 번호를 int로 변환
                revSeatElement.textContent = '좌석번호 : ' + selectedSeatNumber;

                // 숨겨진 입력 필드 업데이트
                revSeatInputElement.value = selectedSeatNumber;
            });

            fishingMapElement.appendChild(fishingElement);

            fishingNumber++; // 다음 좌석 번호로 이동합니다.
        }
        let goUrlCheckout = "/checkout";
        
        let form = document.querySelector("form[name=formList]");
        document.getElementById("Checkoutbtn").onclick = function(){
            form.action = goUrlCheckout;
            form.submit();
        }
    }