$(document).ready(function() {
    /* 테이블의 y축 스크롤 자동 조정 */
    // 화면 높이의 70%를 계산
    var maxHeight = $(window).height() * 0.7;

    // .itemIB-table에 최대 높이 설정 및 overflow-y 속성 추가
    $('.itemIB-table, .itemSemiOB-table', '.itemFinishedOB-table').css({
        'max-height': maxHeight + 'px',
        'overflow-y': 'auto'
    });
    
    /* 테이블의 x축 스크롤 자동 조정 */
    // 화면 너비의 70%를 계산
    var maxWidth = $(window).height() * 0.7;
    // .table에 최대 너비 설정 및 overflow-x 속성 추가
    $('.itemIB-table, .itemSemiOB-table', '.itemFinishedOB-table').css({
			'max-width': maxWidth + 'px',
			'overflow-x': 'auto'
		});


    /* '반제품' 행들 중 마지막 행에 대한 스타일 변경 */
    function addBorderToLastSemiItemRow() {
        // '반제품'이 포함된 모든 행을 찾기
        var $semiItemRows = $('th.semiItemIB-header').closest('tr');

        // 마지막 행에 테두리를 추가
        $semiItemRows.last().children('td').css('border-bottom', '1px solid black');
    }

    // 함수를 호출하여 초기에 스타일을 적용한다.
    addBorderToLastSemiItemRow();

    // 나중에 테이블의 내용이 변경되면 이 함수를 다시 호출해야 함(검색 등)
    /*$.ajax({
    // AJAX 호출 완료후, 함수 호출
    addBorderToLastSemiItemRow(); // 테이블 업데이트 후 호출

    });*/
    
    
    /* Finished Item - 선택한 행을 수정 */
    function editFinishedOBRow(td) {
			// 'td' 요소가 속한 'tr'을 찾기
			var row = td.parentNode;
			while(row.tagName.toLowerCase() !== 'tr') {
				row = row.parentNode;
			}
			
			// 수정 로직(모달창 열기), 일단 alert로 디버깅
			alert('수정로직 구현하기');
		}
		
		editFinishedOBRow(td);

});
