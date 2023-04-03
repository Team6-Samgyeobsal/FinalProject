function showConfirm(msg,detail, callback) {
    let content = `<div class="DialogModal_dialogPortal__30RDP">
  <div
    class="DialogModal_dialogOverlay__3crIB DialogModal_basic__ZSVbj DialogModal_dialogOverlayAfterOpen__R2Uuo"
  >
    <div
      class="DialogModal_dialogContent__2QLwI DialogModal_dialogContentAfterOpen__298u2"
      tabindex="-1"
      role="dialog"
      aria-modal="true"
    >
      <div class="BasicDialog_modalWrap__2cYGd">
        <div class="BasicDialog_modalWrapBox__3r1JK">
          <div class="BasicDialog_dialogBox__1V7Zt">
            <div class="BasicDialog_dialogTitle__3na-I">
              ${msg}
            </div>
            <div class="BasicDialog_dialogMessage__TRYDk">
              <p>
                ${detail}
              </p>
            </div>
            <div
              class="BasicDialog_dialogButtonArea__3j-9J BasicDialog_reverse__2xbZa"
            >
              <button class="wz button BasicDialog_dialogButton__1vFTP primary checkConfirm">
                확인</button
              ><button class="wz button BasicDialog_dialogButton__1vFTP cancelButton ">
                취소
              </button>
            </div>
          </div>
          <button
            type="button"
            class="BasicDialog_modalClose__9TLQu icon-close cancelButton"
            title="닫기"
          ></button>
        </div>
      </div>
    </div>
  </div>
</div>
`
    $('body').append(content);
    $('.cancelButton').click(function (){
        $('.DialogModal_dialogPortal__30RDP').remove();
    })

    $('.checkConfirm').click(function(e){
        callback();
        $('.cancelButton').click();
    })
}
// <script src="/js/confirm.js"></script>
// <link
//     rel="stylesheet"
//     href="/import/css/payments.562cdee9.css"
// />
// <link
//     rel="stylesheet"
//     href="/import/css/wui.css"/>
// <link rel="stylesheet" href="https://static.wadiz.kr/static/web/common.css?a5fa8553">