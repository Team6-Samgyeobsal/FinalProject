function payment() {
    tossPayments.requestPayment("카드", {
        // 결제 수단 파라미터
        // 결제 정보 파라미터
        amount: 15000,
        orderId: "erWQ5P6R67C2iWyvq6xOf",
        orderName: "토스 티셔츠 외 2건",
        customerName: "박토스",
        successUrl: "http://localhost:80/success",
        failUrl: "http://localhost:80/fail"
    });
}



function payment() {
    tossPayments.requestPayment("계좌이체", {
        // 결제 수단 파라미터
        // 결제 정보 파라미터
        amount: 15000,
        orderId: "8a6VXSdB2IXKQUk83-D52",
        orderName: "토스 티셔츠 외 2건",
        successUrl: "http://localhost:80/success",
        failUrl: "http://localhost:80/fail"
    });
}
