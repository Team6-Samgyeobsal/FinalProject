function showToast(msg){

    // $('#toastBody').css('display','flex');
    // $('#toastText').text(msg);
    //
    // setTimeout(function (){
    //     $('#toastBody').css('display','none');
    // },1500)

    let content=`<div class="ToastContainer_container__3oKp3" >
    <div class="Toast_container__2HAXg Toast_show__3Bg04 Toast_warning__3vj31">
        <div class="Toast_content__4BPXL Toast_warning__3vj31">${msg}</div>
    </div>
</div>`
    $('body').append(content);
    setTimeout(function (){
        $('.ToastContainer_container__3oKp3').remove();
        },3000)

}

// <script src="/js/toast.js"></script>
// <link
//     rel="stylesheet"
//     href="/import/css/reward-product.cfd21a1c.css"/>
