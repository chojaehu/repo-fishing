const holidays = [
    { month: 1, day: 1 },
    { month: 3, day: 1 },
    { month: 5, day: 5 },
    { month: 6, day: 6 },
    { month: 8, day: 15 },
    { month: 10, day: 3 },
    { month: 10, day: 9 },
    { month: 12, day: 25 }
];

const toDay    = new Date();    
const nowYear  = toDay.getFullYear(); // 년    
const nowMonth = toDay.getMonth();    // 월
const nowDay   = toDay.getDate();     // 일

// 특정 날짜가 공휴일인지 확인하는 함수
function isHoliday(day, month) {
    return holidays.some(holiday => holiday.month === month + 1 && holiday.day === day);
}

// 특정 날짜가 주말인지 확인하는 함수
function isWeekend(day) {
    return day === 0 || day === 6;
}

// 클릭된 버튼을 기억할 변수
let clickedButton = null;

// 클릭 이벤트 핸들러
function onDateButtonClick(event) {
    const selectedDate = new Date(currentYear, currentMonth, parseInt(event.target.textContent));

    // 오늘 날짜보다 이전인지 확인
    if (selectedDate < toDay) {
        // 오늘 날짜 이전이면 선택 막기
        return;
    }

    // 이전에 클릭된 버튼이 있다면 스타일 초기화
    if (clickedButton) {
        clickedButton.classList.remove('clicked');
    }
    // 현재 클릭된 버튼 스타일 변경
    clickedButton = event.target;
    clickedButton.classList.add('clicked');
    
    const day = event.target.textContent;
    const month = currentMonth + 1;
    const year = currentYear;
    
    // 로컬 스토리지에 날짜 정보 저장
    localStorage.setItem('selectedDate', JSON.stringify({ year, month, day }));
    
    // Hidden input에 날짜 정보 설정
    $("#reservDD").val(day);
    $("#reservMM").val(month);
    $("#reservYY").val(year);
    
    // 페이지 이동
    window.location.href = '/seatList'; // 페이지 이동 주소를 여기에 입력하세요.
}

// 월 변경 및 날짜 갱신 함수
function updateMonth(year, month) {
    const dateContainer = document.getElementById("dateContainer");
    dateContainer.innerHTML = "";
	
    // 월의 마지막일자
    const daysInMonth = new Date(year, month + 1, 0).getDate();
    // 월
    const monthName   = new Date(year, month).toLocaleString('default', { month: 'long' });
    // 년 
    const yearNumber  = new Date(year, month).getFullYear();

    document.getElementById("month").innerHTML = `<span class="month-year">${monthName}</span>${yearNumber}`;

    for (let i = 1; i <= daysInMonth; i++) {
        const button = document.createElement("button");
        button.type = "button";
        
        if(nowYear == yearNumber && ((nowMonth+1)+"월") == monthName && nowDay == i) { // 오늘 비교
	        button.classList.add("btnDay", "date-today");			
		} else {
	        button.classList.add("btnDay", "date-item");
		}
        
        button.textContent = i;

        if (isHoliday(i, month) || isWeekend(new Date(year, month, i).getDay())) {
            button.classList.add("red");
        }

        // 버튼에 클릭 이벤트 추가
        button.addEventListener('click', onDateButtonClick);

        dateContainer.appendChild(button);
    }
}

// 초기화
let currentYear  = nowYear;
let currentMonth = nowMonth;

// 페이지 로드 시 로컬 스토리지에서 날짜 정보 읽기
window.onload = function() {
    const storedDate = localStorage.getItem('selectedDate');
    
    if (storedDate) {
        const { year, month, day } = JSON.parse(storedDate);
        currentYear = parseInt(year);
        currentMonth = parseInt(month) - 1;
        
        updateMonth(currentYear, currentMonth);
        
        // 선택된 날짜 버튼 강조
        const buttons = document.querySelectorAll('.btnDay');
        buttons.forEach(button => {
            if (button.textContent == day) {
                button.classList.add('clicked');
            }
        });
        
        // Hidden input에 날짜 정보 설정
        $("#reservDD").val(day);
        $("#reservMM").val(month);
        $("#reservYY").val(year);
    } else {
        updateMonth(currentYear, currentMonth);
    }
};

document.getElementById("prevMonth").addEventListener("click", () => {
    if (currentMonth === 0) {
        currentMonth = 11;
        currentYear -= 1;
    } else {
        currentMonth -= 1;
    }
    updateMonth(currentYear, currentMonth);
});

document.getElementById("nextMonth").addEventListener("click", () => {
    if (currentMonth === 11) {
        currentMonth = 0;
        currentYear += 1;
    } else {
        currentMonth += 1;
    }
    updateMonth(currentYear, currentMonth);
});
