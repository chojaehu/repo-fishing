// 대한민국의 공휴일 날짜 (간단히 예시로 몇 가지만 지정합니다)
const holidays = [
    { month: 1, day: 1 },
    { month: 3, day: 1 },
    { month: 5, day: 5 },
    { month: 6, day: 6 },
    { month: 8, day: 15 },
    { month: 10, day: 3 },
    { month: 10, day: 9 },
    { month: 12, day: 25 }];
    
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
    // 이전에 클릭된 버튼이 있다면 스타일 초기화
    if (clickedButton) {
        clickedButton.classList.remove('clicked');
    }
    // 현재 클릭된 버튼 스타일 변경
    clickedButton = event.target;
    clickedButton.classList.add('clicked');
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

updateMonth(currentYear, currentMonth);

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