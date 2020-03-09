<button onclick="popupKakaoLogin()">KakaoLogin</button>
<script>
    function popupKakaoLogin() {
        var url = '${loginUrl}';
        var loginUrl = url.replace(/amp;/g,"");

        window.open(loginUrl, 'popupKakaoLogin', 'width=700,height=500,scrollbars=0,toolbar=0,menubar=no')
    }
</script>