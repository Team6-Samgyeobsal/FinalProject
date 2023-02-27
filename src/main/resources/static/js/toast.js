function showToast(msg){
    $('#toastBody').css('display','flex');
    $('#toastText').text(msg);

    setTimeout(function (){
        $('#toastBody').css('display','none');
    },1500)
}