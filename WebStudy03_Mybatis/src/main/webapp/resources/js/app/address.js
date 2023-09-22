/**
 * 
 */

$(function(){
   console.log(this);
//   var contextPath = this.body.dataset.contextPath;
   const cPath = $(this.body).data("contextPath");  //데이터 속성을 이용해서 가져오기 adrsView.jsp
   
   let makeTrTag = function(adrsVO){
      let trTag =`
            <tr data-adrs-no="${adrsVO.adrsNo}">
               <td>${adrsVO.adrsName}</td>
               <td>${adrsVO.adrsHp}</td>
               <td>${adrsVO.adrsAdd}</td>
               <td><input type="button" value="삭제" class="delBtn"/></td>
            </tr>
         `;
      return trTag;
   };
   
   const baseUrl = `${cPath}/adrs/address`;

   $.getJSON(baseUrl, function(resp){
   //   메소드가 이미 get, 데이터타입은 이미 json- 나머지조건만 주면 됨
      let adrsList = resp.adrsList;
      trTags="";
      if(adrsList?.length>0){
      //adrsList&&adrsList.length>0
         $.each(adrsList,function(){
            trTags+= makeTrTag(this);
            
            
         });
      }else{
         trTags += `
            <tr>
               <td colspan='3'>주소록 없음.</td>
            </tr>
         `;
      }// if~else end
      $(listBody).html(trTags);
   });   //getJSON end
   

   //추가
   $(adrsForm).on('submit',function(event){
      event.preventDefault();
      let url = this.action;
      let method = this.method;
      let data = $(this).serializeJSON(); // 폼에서 입력한 데이터 값을 json직렬화  
      let json = JSON.stringify(data); //이전 단계에서 생성된 json객체를 문자열로 직렬화 (Ajax 요청 메서드 사용할때 데이터를 서버로 전송하려면 문자열 형태로 변환해야함)
      let settings = {
         url : baseUrl,
         method : method,
         data : json,
         headers :{
            "Content-Type":"application/json; charset=UTF-8" //Content-Type헤더를 JSON으로 지정, 서버는 요청 본문을 json형식으로 해석하고 문자를 인코딩을 사용해 데이터 처리
         },
         dataType : "json"  //서버로부터 받을 응답 데이터의 형식
         
      };
      
      $.ajax(settings)
         .done(function(resp){ //ajax요청이 성공하면 실행할 콜백함수 정의!!

            if(resp.success){ // 서버에서 받은 응답데이터의 success 속성을 확인하여 응답이 성공적인지 여부를 판단
               let trTag = makeTrTag(resp.originalData); //서버에서 반환된 originalData데이터
               $(listBody).prepend(trTag); //listbody 가장 앞에 추가
               adrsForm.reset(); //폼 리섹하여 다음 입력 준비
            }else{
               alert(resp.message);
            }
         });
      
      return false;
   })
   
   
   $(listBody).on('click','.delBtn',function(){  //동적 클릭이벤트
      let flag = confirm("삭제 할텨?");
      if(!flag) return false;  //취소 선택한 경우 함수 종료
      
      let adrsTr = $(this).parents("tr:first"); // 삭제버튼(this)부모 중에서 첫번째 tr태그 
      let $adrsTr = $(adrsTr);
      let adrsNo = $adrsTr.data("adrsNo");   // 데이터 속성 값 가져오기 <tr data-adrs-no="${adrsVO.adrsNo}"> js파일
      let url = `${baseUrl}/${adrsNo}`;
      
      let settings = {
         url : url,
         method : "delete",
         dataType : "json"
      }
      
      $.ajax(settings)
         .done(function(resp){  //콜백함수
            if(resp.success){
               $adrsTr.remove();
            }else{
               alert(resp.message)
            }
         })
   });
   
});