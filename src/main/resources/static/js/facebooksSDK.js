$(document).ready(function () {
    $.ajaxSetup({cache: true});
    $.getScript('//connect.facebook.net/en_US/sdk.js', function () {
        FB.init({
            appId: '346049182393731',
            version: 'v2.5' // or v2.0, v2.1, v2.2, v2.3
        });
        $('#loginbutton,#feedbutton').removeAttr('disabled');
        FB.getLoginStatus(updateStatusCallback);
    });

    function checkLoginState() {
        FB.getLoginStatus(function (response) {
            statusChangeCallback(response);
        });
    }

    //facebook 로그인
    $("#facebooksso").on('click', function () {
            console.info("facebook SSO를 시도합니다.");
            fb_login();
            checkLoginState();
        }
    );

    function fb_login() {
        FB.login(function (response) {

            if (response.authResponse) {
                console.log('Welcome!  Fetching your information.... ');
                //console.log(response); // dump complete info
                access_token = response.authResponse.accessToken; //get access token
                user_id = response.authResponse.userID; //get FB UID

                FB.api('/me', function (response) {
                    user_email = response.email; //get user email
                    // you can store this data into your database
                });

            } else {
                //user hit cancel button
                console.log('User cancelled login or did not fully authorize.');
            }
        }, {
            scope: 'public_profile, email' // 이 경우 사용자의 이메일 주소와 앱을 사용하는 친구 리스트를 요청합니다.
        });
    }

    (function () {
        var e = document.createElement('script');
        e.src = document.location.protocol + '//connect.facebook.net/en_US/all.js';
        e.async = true;
        document.getElementById('fb-root').appendChild(e);
    }());

    function statusChangeCallback(response) {
        console.log('statusChangeCallback');
        console.log(response);
        // The response object is returned with a status field that lets the
        // app know the current login status of the person.
        // Full docs on the response object can be found in the documentation
        // for FB.getLoginStatus().
        if (response.status === 'connected') {
            // Logged into your app and Facebook.
            testAPI();
        } else if (response.status === 'not_authorized') {
            // The person is logged into Facebook, but not your app.
            document.getElementById('status').innerHTML = 'Please log ' +
                'into this app.';
        } else {
            // The person is not logged into Facebook, so we're not sure if
            // they are logged into this app or not.
            document.getElementById('status').innerHTML = 'Please log ' +
                'into Facebook.';
        }
    }

    //로그인후 필드 접근 방법은 http://stackoverflow.com/questions/32436662/email-scope-is-not-working-which-is-used-in-fb-login 참조
    function testAPI() {
        console.log('Welcome!  Fetching your information.... ');
        FB.api('/me', {fields: 'id,name,email'}, function (response) {
            console.log('Successful login for: ' + response.email);
            console.log(access_token);
            console.log(JSON.stringify(response));
            accessToLacsLogin(response.id, response.email);
        });
    }

    function accessToLacsLogin(id, email) {
        $.ajax({
            type: "POST",
            url: "./authenticationFacebook",
            data: {"id": id, "email": email},
            dataType: "html",
            success: function (data) {
                console.log(data);
                if (data === "true") {
                    window.location.replace("currentSeminar");
                }
                else {
                    console.info("로그인실패");
                }
            }
        });
    }

});