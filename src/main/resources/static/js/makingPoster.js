$(window).load(function () {

    // Initialize Firebase
    var config = {
        apiKey: "AIzaSyBj5cWfdcKeUJULThR9ppZhKkn5f9Sog-8",
        authDomain: "lacs-b35d9.firebaseapp.com",
        databaseURL: "https://lacs-b35d9.firebaseio.com",
        storageBucket: "lacs-b35d9.appspot.com",
    };
    firebase.initializeApp(config);

    // Get a reference to the storage service, which is used to create references in your storage bucket
    var storage = firebase.storage();

    // Create a storage reference from our storage service
    var storageRef = storage.ref();

    
    
    //파일 업로드(save) 버튼 클릭시 하기 위한 기능
    $("#inputFileSave").on("click",function () {
        alert("저장시작");
        // File or Blob, assume the file is called rivers.jpg
        var inputfile = $("#inputFile");
        var file = inputfile[0].files[0];
        var title = $("#hiddenText");
        // Upload file and metadata to the object 'images/mountains.jpg'
        var uploadTask = storageRef.child(file.name).put(file);

// Listen for state changes, errors, and completion of the upload.
        uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED, // or 'state_changed'
            function(snapshot) {
                // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
                var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
                console.log('Upload is ' + progress + '% done');
                switch (snapshot.state) {
                    case firebase.storage.TaskState.PAUSED: // or 'paused'
                        console.log('Upload is paused');
                        break;
                    case firebase.storage.TaskState.RUNNING: // or 'running'
                        console.log('Upload is running');
                        break;
                }
            }, function(error) {
                switch (error.code) {
                    case 'storage/unauthorized':
                        // User doesn't have permission to access the object
                        break;

                    case 'storage/canceled':
                        // User canceled the upload
                        break;

                        case 'storage/unknown':
                        // Unknown error occurred, inspect error.serverResponse
                        break;
                }
            }, function() {
                // Upload completed successfully, now we can get the download URL
                var downloadURL = uploadTask.snapshot.downloadURL;
                alert(file.name+"업로드완료!");
                uploadPosterResourceByRest("test1","test2");
                location.reload(); //페이지 다시실행
            });
    });

// 진행바 관리.
    $("#uploadedFile").text(storageRef);
    if ($(".progress-bar").text() == 0) {
        restart();
    } else if ($(".progress-bar").text() == 100) {
        complete();
    }

    /**
     * 포스터 작업의 버튼
     */

    $("#PosterComplete").on("click", function () {
        complete();
        progressUpdate(100);
    });

    $("#PosterRestart").on("click", function () {
        restart();
        progressUpdate(0);
    });

    /**
     * 완료시
     * 버튼 Restart -> Complete
     * 진행바 100%로
     */

    function complete() {
        $("#PosterComplete").css("display", "none");
        $("#PosterRestart").css("display", "block");
        $(".progress-bar").css("width", "100%");
        $(".progress-bar").text("100%");

    }

    /**
     * 취소시
     * 버튼 Complete -> Restart
     * 진행바 0%로
     */

    function restart() {
        $("#PosterRestart").css("display", "none");
        $("#PosterComplete").css("display", "block");
        $(".progress-bar").css("width", "0%");
        $(".progress-bar").text("0%");
    }

    /**
     * progress update
     */
    function progressUpdate(progress) {
        console.log("progressUpdate 눌림");
        $.ajax({
            type: "POST",
            url: "/updatePosterTask",
            data: {"progress": progress},
            dataType: "html",
            success: function () {
                alert("Data Saved");
            }
        });
    }

    function uploadPosterResourceByRest(inputFile, title) {
        alert("업데이트반영중!");
        $.ajax({
            type: "POST",
            url: "./uploadPosterResourceByRest",
            data: {"inputFile":inputFile,"title":title},
            dataType: "html",
            success: function () {
                alert("업데이트성공!");
            }
        });
    }

});
