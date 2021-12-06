var currentUser;
var currentStudyId;
var currentStudyName;
var currentAuthUserId;

////////////////MAIN PAGE//////////////////////////

function handleLoginClick() {
    window.location.href = "login.html";
}

function handleRegisterClick() {
    window.location.href = "register.html";
}

////////////////LOGIN PAGE/////////////////////////////////////////

function checkLogin() {
    var user = document.getElementById("loginUsername").value;
    var pass = document.getElementById("loginPassword").value;

    if (user == "") {
        alert("Please enter a username before continuing");
        return false;
    }

    if (pass == "") {
        alert("Please enter a password before continuing");
        return false;
    }

    var json = {username: user, password: pass};

    var js = JSON.stringify(json);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", Login_url, true);

    console.log("after post");
    // send the collected data as JSON
    xhr.send(js);
    console.log("after send");
    // This will process results and update HTML as appropriate.
    xhr.onloadend = function() {
        console.log("in function");
        console.log("XHR:" + xhr);
        console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            processLoginResponse(xhr.responseText);
        } else {
            console.log("got an error");
            processLoginResponse("N/A");
        }
    };

    return false;
}

function processLoginResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var username = js["username"];
    currentUser = username;

    if (status == 200) {
        window.location.href = "userHome.html";
        // window.onload = checkUsername(username);
    } else {
        var msg = js["error"];
        console.log("error:" + msg);

        var textString = "<p> error: " + msg + "</p>";
        loginText = document.getElementById("loginText");
        loginText.innerHTML = textString;
    }
}

////////////////REGISTER PAGE/////////////////////////////////////////

function checkRegister() {
    var user = document.getElementById("regUsername").value;
    var eMail = document.getElementById("regEmail").value;
    var pass = document.getElementById("regPassword").value;
    var pass2 = document.getElementById("regPasswordConfirm").value;

    if (user == "") {
        alert("Please enter a username before continuing");
        return false;
    }

    if (eMail == "") {
        alert("Please enter an email before continuing");
        return false;
    }

    if (pass == "") {
        alert("Please enter a password before continuing");
        return false;
    }

    if (pass2 == "") {
        alert("Please confirm your password before continuing");
        return false;
    }

    if (pass != pass2) {
        alert("The passwords entered are not the same");
        return false;
    }

    var json = {username: user, password: pass, email: eMail};

    var js = JSON.stringify(json);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", Register_url, true);

    console.log("after post");
    // send the collected data as JSON
    xhr.send(js);
    console.log("after send");
    // This will process results and update HTML as appropriate.
    xhr.onloadend = function() {
        console.log("in function");
        console.log("XHR:" + xhr);
        console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            processRegisterResponse(xhr.responseText);
        } else {
            console.log("got an error");
            processRegisterResponse("N/A");
        }
    };

    return false;
}

function processRegisterResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var username = js["username"];
    currentUser = username;

    if (status == 200) {
        window.location.href = "userHome.html";

        // var id = choice[0];
        // id = id["cid"];

        // var choiceText = document.createElement("p");

        // var textString = "CHOICE ID IS: <b>" +
        //     id +
        //     "</b><br>Please copy and paste this to access your newly created choice.<br> Afterwards navigate to Homepage or Sign into Choice";


        // choiceText.innerHTML = textString;

        // //make button ID cid to easily add functionality later on
        // choiceText.id = id;
        // // Insert text
        // document.body.appendChild(choiceText);
    } else {
        var msg = js["error"];
        console.log("error:" + msg);

        var textString = "<p> error: " + msg + "</p>";
        regText = document.getElementById("regText");
        regText.innerHTML = textString;
    }
}

////////////////NEW STUDY PAGE/////////////////////////////////////////

function checkNewStudy() {
    var inputStudyName = document.getElementById("inputStudyName").value;
    var inputShortStudyName = document.getElementById("inputShortStudyName").value;
    var inputStudyAbstract = document.getElementById("inputStudyAbstract").value;
    var aid = "74a00780-3a82-4a32-bf61-7d03b4860e89";

    if (inputStudyName == "") {
        alert("Please enter a study name before continuing");
        return false;
    }

    // if (inputStudyAbstract == "") {
    //     alert("Please enter a study abstract before continuing");
    //     return false;
    // }

    // if (inputShortStudyName == "") {
    //     alert("Please enter a short study name before continuing");
    //     return false;
    // }

    var json = {name: inputStudyName, shortName: inputShortStudyName, studyAbstract: inputStudyAbstract, authUserId: aid};

    var js = JSON.stringify(json);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", CreateStudy_url, true);

    console.log("after post");
    // send the collected data as JSON
    xhr.send(js);
    console.log("after send");
    // This will process results and update HTML as appropriate.
    xhr.onloadend = function() {
        console.log("in function");
        console.log("XHR:" + xhr);
        console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            processNewStudyResponse(xhr.responseText);
        } else {
            console.log("got an error");
            processNewStudyResponse("N/A");
        }
    };

    return false;
}

function processNewStudyResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var study = js["study"];

    if (status == 200) {
        window.location.href = "userHome.html";
    } else {
        var msg = js["error"];
        console.log("error:" + msg);

        var textString = "<p> error: " + msg + "</p>";
        newStudyText = document.getElementById("newStudyText");
        renewStudyTextgText.innerHTML = textString;
    }
}

// function checkUsername(username) {
//     welcomeUser = document.getElementById("welcomeUser");
//     welcomeUser.innerHTML = username;
// }

// window.onload = function() {
//     welcomeUser = document.getElementById("welcomeUser");
//     welcomeUser.innerHTML = currentUser;
// }

////////////////USER HOME PAGE/////////////////////////////////////////

function checkGetStudies() {
    // var authUserName = document.getElementById("").value;
    var aid = "74a00780-3a82-4a32-bf61-7d03b4860e89";

    var json = {authUserId: aid};

    var js = JSON.stringify(json);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", getStudies_url, true);

    console.log("after post");
    // send the collected data as JSON
    xhr.send(js);
    console.log("after send");
    // This will process results and update HTML as appropriate.
    xhr.onloadend = function() {
        console.log("in function");
        console.log("XHR:" + xhr);
        console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            processGetStudiesResponse(xhr.responseText);
        } else {
            console.log("got an error");
            processGetStudiesResponse("N/A");
        }
    };

    return false;
}

function processGetStudiesResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var studies = js["studies"];

    if (status == 200) {
        console.log("getStudies status 200");

        var studyTable = document.getElementById("tableOfStudiesOnUserHome");
        var study;
        var tableString = "";
  
        for(let i = 0; i < studies.length; i++){
            study = studies[i];
            //studyName = study["studyName"]
            //studyShortName = study["studyShortName"]
            //abstract = study["studyDescription"]
            //by = study["authUserId"]

            //create row to be inserted
            tableString += "<tr onclick='JavaScript:studyClicked(";
            tableString += '"';
            tableString += study["studyId"];
            tableString += '", "';
            tableString += study["authUserId"];
            tableString += '", "';
            tableString += study["studyName"];
            tableString += '")';
            tableString += "'>";
            tableString += "<td>";
            tableString += study["studyName"];
            tableString += "</td>";
            tableString += "<td>";
            tableString += study["studyShortName"];
            tableString += "</td>";
            tableString += "<td>";
            tableString += study["studyDescription"];
            tableString += "</td>";
            tableString += "<td>";
            tableString += study["authUserId"];
            tableString += "</td>";
            tableString += "</tr>";           
        }
        // tableString += "</tbody>";
        console.log(tableString);

        studyTableTbody = document.getElementById("studyTableTbody");
        studyTableTbody.innerHTML = tableString;
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
    }
}

function studyClicked(studyId, authUserId, studyName) {
    console.log("in studyClicked");
    console.log("studyId: " + studyId);
    console.log("authUserId: " + authUserId);
    currentStudyId = studyId;
    currentAuthUserId = authUserId;
    currentStudyName = studyName;
    window.location.href = "studyPage.html";
}

function checkGetData() {
    // var authUserName = document.getElementById("").value;
    // var aid = "5d5ef618-06ef-4667-ad40-34ad4cf02da5";

    var studyNameOnStudyPage = document.getElementById("studyNameOnStudyPage");
    console.log("currrentStudyName: " + currentStudyName);
    console.log("studyNameOnStudyPage: " + studyNameOnStudyPage);
    console.log("innerhtml: " + studyNameOnStudyPage.innerHTML);
    studyNameOnStudyPage.innerHTML = currentStudyName;

    var json = {authUserId: currentAuthUserId, studyId: currentStudyId};

    var js = JSON.stringify(json);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", GetData_url, true);

    console.log("after post");
    // send the collected data as JSON
    xhr.send(js);
    console.log("after send");
    // This will process results and update HTML as appropriate.
    xhr.onloadend = function() {
        console.log("in function");
        console.log("XHR:" + xhr);
        console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            processGetDataResponse(xhr.responseText);
        } else {
            console.log("got an error");
            processGetDataResponse("N/A");
        }
    };

    return false;
}

function processGetDataResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var data = js["data"];

    if (status == 200) {
        console.log("getData status 200");

        // var studyTable = document.getElementById("tableOfStudiesOnUserHome");
        // var study;
        // var tableString = "";
  
        // for(let i = 0; i < studies.length; i++){
        //     study = studies[i];
        //     //studyName = study["studyName"]
        //     //studyShortName = study["studyShortName"]
        //     //abstract = study["studyDescription"]
        //     //by = study["authUserId"]

        //     //create row to be inserted
        //     tableString += "<tr onclick='JavaScript:studyClicked(";
        //     tableString += '"';
        //     tableString += study["studyId"];
        //     tableString += '", "';
        //     tableString += study["authUserId"];
        //     tableString += '", "';
        //     tableString += study["studyName"];
        //     tableString += '")';
        //     tableString += "'>";
        //     tableString += "<td>";
        //     tableString += study["studyName"];
        //     tableString += "</td>";
        //     tableString += "<td>";
        //     tableString += study["studyShortName"];
        //     tableString += "</td>";
        //     tableString += "<td>";
        //     tableString += study["studyDescription"];
        //     tableString += "</td>";
        //     tableString += "<td>";
        //     tableString += study["authUserId"];
        //     tableString += "</td>";
        //     tableString += "</tr>";           
        // }
        // // tableString += "</tbody>";
        // console.log(tableString);

        // studyTableTbody = document.getElementById("studyTableTbody");
        // studyTableTbody.innerHTML = tableString;
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
    }
}