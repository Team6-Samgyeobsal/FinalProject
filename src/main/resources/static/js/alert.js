function showAlert(msg){
    let content=`<div class="DialogModal_dialogPortal__1rRI2">
    <div class="DialogModal_dialogOverlay__3PgUV DialogModal_dialogOverlayAfterOpen__1fWcT">
        <div class="DialogModal_dialogContent__3P4An DialogModal_dialogContentAfterOpen__EjXJ4" tabindex="-1"
             role="dialog" aria-modal="true">
            <div class="Dialog_dialogWrap__2x9kS">
                <div class="Dialog_dialogBox__yCpT4">
                    <div class="Dialog_dialogTextBox__Cxk45">
                        <div class="Dialog_dialogMessage__TLIV9"><p>${msg}</p></div>
                    </div>
                    <div class="Dialog_dialogButtonArea__3x6Ry">
                        <button type="button"
                                class="Dialog_dialogButton__E0TnP Dialog_dialogButtonPrimary__3w6ow Dialog_dialogButtonReverse__1hgC5">
                            <span>확인</span></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>`;
    $('body').append(content);
    $('.Dialog_dialogButtonReverse__1hgC5').click(function (){
        $('.DialogModal_dialogPortal__1rRI2').remove();
    })
}
// <script src="/js/alert.js"></script>
// <link
//     rel="stylesheet"
//     href="/import/css/layout.css"
// />

