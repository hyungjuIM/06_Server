// 유효성 검사 여부를 기록할 객체 생성
 const checkobj = {
    "memberEmail" : false,
    "memberPw" : false,
    "memberPwConfirm" : false,
    "memberNickname" : false,
    "memberTel" : false,
    "sendEmail" : false
 };

 // 이메일 유효성 검사
const memberEmail = document.getElementById("memberEmail");
const emailMessage = document.getElementById("emailMessage");

memberEmail.addEventListener("input", function(){

    // 입력이 되지 않은 경우
    if(memberEmail.value.length == 0){
        emailMessage.innerText ="메일을 받을 수 있는 이메일을 입력해 주세요";
        
        emailMessage.classList.remove("confirm","error");
        checkobj.memberEmail = false; // 유효x 기록
        return;
    }
    
    // 입력이 된 경우
    const regExp = /^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/;
    if(regExp.test(memberEmail.value)){ // 유효o
        //**************** 이메일 중복 검사(ajax)*************
        $.ajax({
            url : "emailDupCheck",
            // 필수 속성 url
            // 현재 주소 : /community/member/signUp
            // 상대 경로 : /community/member/emailDupCheck

            data : {"member" : memberEmail.value},
            // data 속성 : 비동기 통신 시 서버로 전달한 값을 작성 (JS객체 형식)
            // -> 비동기 통신 시 input에 입력된 값을
            // "memberEmail" 이라는 key값(파라미터)으로 전달
            success : function(result){
                // 비동기 통신(ajax0)가 오류 없이 요청/응답 성공한 경우

                // 매개변수 result : servlet에서 출력된 값이 담겨 있음.
                console.log(result);

                if(result == 1){ // 중복
                    emailMessage.innerText="이미 사용중인 이메일입니다.";
                    emailMessage.classList.add("error");
                    emailMessage.classList.remove("confirm");
                    checkobj.memberEmail = false;
                }else{ // 중복x
                    emailMessage.innerText="사용 가능한 이메일입니다.";
                    emailMessage.classList.remove("error");
                    emailMessage.classList.add("confirm");
                    checkobj.memberEmail = true; // 유효o 기록
                }               

            } ,
            error : function(){
                console.log("에러발생")

            }
        })
        
    }else{
        emailMessage.innerText ="이메일 형식이 유효하지 않습니다";
        emailMessage.classList.add("error");
        emailMessage.classList.remove("confirm");
        
        checkobj.memberEmail = false; // 유효x 기록
        
    }
})


// 정규표현식

// 인증번호 받기
const sendBtn = document.getElementById("sendBtn");

sendBtn.addEventListener("click",function(){ // 유효한 이메일이 작성되어 있을 경우에만 메일 보내기
    if(checkobj.memberEmail){
    $.ajax({
        url : "sendEmail",
        data : {"inputEmail" : memberEmail.value},
        success : function(result){
            console.log("이메일 발송 성공")
            console.log(result);

            // 인증버튼이 클릭되어 정상적으로 메일이 보내졌음을
            checkobj.sendEmail = true;

        },
        error : function(){
            console.log("이메일 발송 실패")
        }
    })
    }
});

