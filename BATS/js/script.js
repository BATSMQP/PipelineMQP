var content;
//beforeUpload

////////////////MAIN PAGE//////////////////////////

function handleLoginClick() {
    window.location.href = "login.html";
}

function handleRegisterClick() {
    window.location.href = "register.html";
}

////////////////LOGIN PAGE/////////////////////////////////////////

function setWelcomeUser(){
    document.getElementById('welcomeUser').innerHTML = localStorage.getItem('currentUser');
    console.log("currentAuthUserId in checkNewStudy: " + localStorage.getItem('currentAuthUserId'));
}

function loadStudyPage(){
    localStorage.setItem("beforeUpload", "studyPage");
}

function loadSelectData(){
    localStorage.setItem("beforeUpload", "selectData");
}

function loadSelectAlgorithms(){
    localStorage.setItem("beforeUpload", "selectAlgorithms");
    localStorage.setItem("selectAlgsTableString", document.getElementById("selectAlgsToolkits"));
}

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
            console.log("currentAuthUserId in checkLogin: " + localStorage.getItem('currentAuthUserId'));
        }
    };

    return false;
}

function processLoginResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var username = js["username"];
    var authUserId = js["authUserId"];
    localStorage.setItem('currentUser', username);
    localStorage.setItem('currentAuthUserId', authUserId);
    console.log("currentAuthUserId in processLoginResponse: " + localStorage.getItem("authUserId"));
    console.log("currentUser in processLoginResponse: " + localStorage.getItem("currentUser"));

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
    var authUserId = js["authUserId"];
    localStorage.setItem('currentUser', username);
    localStorage.setItem('currentAuthUserId', authUserId);

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
    var inputStudyInstitutionsInvolved = document.getElementById("inputStudyInstitutionsInvolved").value;
    var inputStudyStudyContact = document.getElementById("inputStudyStudyContact").value;
    var inputStudyStudyNotes = document.getElementById("inputStudyStudyNotes").value;
    var inputStudyIsIrbApprovedYes = document.getElementById("inputStudyIsIrbApprovedYes").value;
    var inputStudyIsIrbApprovedNo = document.getElementById("inputStudyIsIrbApprovedNo").value;
    var inputStudyVisibilityYes = document.getElementById("inputStudyVisibilityYes").value;
    var inputStudyVisibilityNo = document.getElementById("inputStudyVisibilityNo").value;

    if (inputStudyName == "") {
        alert("Please enter a study name before continuing");
        return false;
    }

    var inputStudyIsIrbApproved = "";
    if(inputStudyIsIrbApprovedYes){
        inputStudyIsIrbApproved = "yes";
    } else if(inputStudyIsIrbApprovedNo){
        inputStudyIsIrbApproved = "no";
    }

    var inputStudyVisibility = "";
    if(inputStudyVisibilityYes){
        inputStudyVisibility = "yes";
    } else if(inputStudyVisibilityNo){
        inputStudyVisibility = "no";
    }

    console.log("currentAuthUserId in checkNewStudy: " + localStorage.getItem('currentAuthUserId'));

    var json = {name: inputStudyName, shortName: inputShortStudyName, studyAbstract: inputStudyAbstract, institutionsInvolved: inputStudyInstitutionsInvolved, studyContact: inputStudyStudyContact, studyNotes: inputStudyStudyNotes, isIrbApproved: inputStudyIsIrbApproved, visibility: inputStudyVisibility, authUserId: localStorage.getItem('currentAuthUserId')};

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

////////////////USER HOME PAGE/////////////////////////////////////////

function checkGetUsername(aid) {
    var result = "";
    var json = {authUserId: aid};

    var js = JSON.stringify(json);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", GetUsername_url, true);

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
            result = processGetUsernameResponse(xhr.responseText);
        } else {
            console.log("got an error");
            result = processGetUsernameResponse("N/A");
        }
    };

    return result;
}

function processGetUsernameResponse(result) {
    var r = "";
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var username = js["username"];
    console.log("in processGetUsernameResponse username: " + username);

    if (status == 200) {
        r = username;
        localStorage.setItem("usernameOfAU", username);
        console.log("in processGetUsernameResponse r: " + r);
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
    }
    return r;
}

function checkGetStudies() {
    console.log("currentAuthUserId in checkGetStudies: " + localStorage.getItem('currentAuthUserId'));
    console.log("currentUser in checkGetStudies: " + localStorage.getItem('currentUser'));

    var json = {authUserId: localStorage.getItem('currentAuthUserId'), username: localStorage.getItem('currentUser')};

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
            checkGetUsername(study["authUserId"]);
            console.log("in processGetStudiesResponse usernameOfAU: " + localStorage.getItem("usernameOfAU"));


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
                tableString += study["studyDescription"];
                tableString += "</td>";
                tableString += "<td>";
                tableString += localStorage.getItem("usernameOfAU");
                tableString += "</td>";
                tableString += "<td>";
                tableString += study["studyContact"];
                tableString += "</td>";
                tableString += "<td>";
                tableString += study["institutionsInvolved"];
                tableString += "</td>";
                tableString += "<td>";
                tableString += study["studyNotes"];
                tableString += "</td>";
                tableString += "<td>";
                tableString += study["isIrbApproved"];
                tableString += "</td>";
                tableString += "<td>";
                tableString += study["visibility"];
                tableString += "</td>";
                tableString += "<td>";
                tableString += "date";
                tableString += "</td>";
                tableString += "</tr>";                    
        }
        // tableString += "</tbody>";
        // console.log(tableString);

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
    console.log("studyName (in studyClicked): " + studyName);
    localStorage.setItem('currentStudyId', studyId);
    // localStorage.setItem('currentAuthUserId', authUserId);
    localStorage.setItem('studyName', studyName);
    currentStudyName = localStorage.getItem('studyName');
    currentStudyId = localStorage.getItem('currentStudyId');
    console.log("currentStudyName (in studyClicked): " + currentStudyName);
    console.log("currentStudyId (in studyClicked): " + currentStudyId);
    window.location.href = "studyPage.html";
}

function checkGetData() {
    // var studyNameOnStudyPage = document.getElementById("studyNameOnStudyPage");
    // currentStudyName = localStorage.getItem('studyName');
    // console.log("currrentStudyName (in checkGetData): " + currentStudyName);
    // console.log("innerhtml of studyNameOnStudyPage (in checkGetData): " + studyNameOnStudyPage.innerHTML);
    // studyNameOnStudyPage.innerHTML = currentStudyName;

    var json = {authUserId: localStorage.getItem('currentAuthUserId')};

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
    var studies = js["studies"];

    if (status == 200) {
        console.log("getData status 200");

        var tableString = "";
        var doc;
  
        for(let i = 0; i < data.length; i++){
                doc = data[i];
                //create row to be inserted
                tableString += "<tr>";

                tableString += "<td>";
                tableString += doc["name"];
                tableString += "</td>";

                tableString += "<td>";
                tableString += doc["filename"];
                tableString += "</td>";

                tableString += "<td>";
                tableString += doc["dataType"];
                tableString += "</td>";

                tableString += "<td>";
                tableString += studies[i]["studyName"];
                tableString += "</td>";

                tableString += "</tr>";                    
        }

        DataTableBody = document.getElementById("ViewDataTableBody");
        DataTableBody.innerHTML = tableString;

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
////////////////Select Data Page////////////////////////////////////////////////////////////////////

function checkGetStudyData() {
    var studyNameOnStudyPage = document.getElementById("studyNameOnStudyPage");
    currentStudyName = localStorage.getItem('studyName');
    console.log("currrentStudyName (in checkGetData): " + currentStudyName);
    // console.log("innerhtml of studyNameOnStudyPage (in checkGetData): " + studyNameOnStudyPage.innerHTML);
    // studyNameOnStudyPage.innerHTML = currentStudyName;

    var json = {studyId: localStorage.getItem('currentStudyId')};

    var js = JSON.stringify(json);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", GetStudyData_url, true);

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
            processGetStudyDataResponse(xhr.responseText);
        } else {
            console.log("got an error");
            processGetStudyDataResponse("N/A");
        }
    };

    return false;
}

function processGetStudyDataResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var data = js["data"];
    localStorage.setItem("currentData", data);

    if (status == 200) {
        console.log("getStudyData status 200");

        var dataTable = document.getElementById("DataTable");
        var tableString = "";
        var doc;
  
        for(let i = 0; i < data.length; i++){
                doc = data[i];
                //create row to be inserted
                tableString += "<tr onclick='JavaScript:dataClicked(";
                tableString += '"';
                tableString += doc["documentId"];
                tableString += '", "';
                tableString += doc["authUserId"];
                tableString += '", "';
                tableString += doc["filename"];
                tableString += '", "';
                tableString += doc["name"];
                tableString += '", "';
                tableString += i;
                tableString += '")';
                tableString += "'>";

                tableString += "<td>";
                tableString += doc["name"];
                tableString += "</td>";

                tableString += "<td>";
                tableString += doc["filename"];
                tableString += "</td>";

                tableString += "<td>";
                tableString += doc["dataType"];
                tableString += "</td>";

                tableString += "</tr>";                    
        }

        DataTableBody = document.getElementById("DataTableBody");
        DataTableBody.innerHTML = tableString;
        localStorage.setItem("dataTableString", tableString);
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
    }
}

function dataClicked(documentId, authUserId, filename, name, chosenI) {
    console.log("in dataClicked");
    console.log("documentId: " + documentId);
    console.log("authUserId: " + authUserId);
    console.log("filename (in dataClicked): " + filename);
    console.log("name (in dataClicked): " + name);
    localStorage.setItem('currentDataDocumentId', documentId);
    // localStorage.setItem('currentAuthUserId', authUserId);
    localStorage.setItem('currentDataFilename', filename);
    localStorage.setItem('currentDataName', name);
    // localStorage.setItem('currentDatai', i);
    currentFileName = localStorage.getItem('filename');
    currentName = localStorage.getItem('name');
    currentDataDocumentId = localStorage.getItem('currentDataDocumentId');
    console.log("currentFileName (in dataClicked): " + currentFileName);
    console.log("currentName (in dataClicked): " + currentName);
    console.log("currentDataDocumentId (in dataClicked): " + currentDataDocumentId);
    // console.log("data (in dataClicked): ")
    // console.log(data[name]);
    // window.location.href = "selectAlgorithms.html";

    //make selected row active
    var tableString = localStorage.getItem("dataTableString");
    var splitter = "onclick='JavaScript:dataClicked(";
    splitter += '"';
    splitter += documentId;
    splitter += '", "';
    splitter += authUserId;
    splitter += '", "';
    splitter += filename;
    splitter += '", "';
    splitter += name;
    splitter += '", "';
    splitter += chosenI;
    splitter += '")';
    var tsSplit2 = tableString.split(splitter);
    var newTableString = tsSplit2[0] + "class='table-active' " + tsSplit2[1];
    DataTableBody = document.getElementById("DataTableBody");
    DataTableBody.innerHTML = newTableString;
}
/////////////////Select Algorithm PAGE///////////////////////////////////////////////////////////

function algoClicked(toolDocumentId, algName, name) { //algName=filename
    console.log("in algoClicked");
    console.log("toolDocumentId: " + toolDocumentId);
    console.log("algName (in algoClicked): " + algName);
    localStorage.setItem('currenToolDocumentId', toolDocumentId);
    // localStorage.setItem('currentAuthUserId', authUserId);
    localStorage.setItem('algName', algName);
    localStorage.setItem('currentToolName', name);
    currentAlgName = localStorage.getItem('algName');
    currentToolDocumentId = localStorage.getItem('currentToolDocumentId');
    console.log("currentAlgName (in algoClicked): " + currentAlgName);
    console.log("currentToolDocumentId (in algoClicked): " + currentToolDocumentId);
    // window.location.href = "algoResult.html";

    //make selected row active
    // var tableString = localStorage.getItem("selectAlgsTableString");
    // var splitter = "onclick='JavaScript:algoClicked(";
    // splitter += '"';
    // splitter += toolDocumentId;
    // splitter += '", "';
    // splitter += algName;
    // splitter += '", "';
    // splitter += name;
    // splitter += '")';
    // splitter += "'";
    // var tsSplit2 = tableString.split(splitter);
    // var newTableString = tsSplit2[0] + "class='table-active' " + tsSplit2[1];
    // selectAlgsToolkits = document.getElementById("selectAlgsToolkits");
    // selectAlgsToolkits.innerHTML = newTableString;
    // console.log("selectAlgsToolkits: " + selectAlgsToolkits);
}

function checkRunAlg() {
    var json = {dataDocumentId: localStorage.getItem('currentDataDocumentId'), toolDocumentId: "", algName: localStorage.getItem('currentAlgName')};

    var js = JSON.stringify(json);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", RunAlg_url, true);

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
            processRunAlgResponse(xhr.responseText);
        } else {
            console.log("got an error");
            processRunAlgResponse("N/A");
        }
    };

    return false;
}

function processRunAlgResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var resultFile = js["resultFile"];
    var image = js["image"];


    if (status == 200) {
        console.log("RunAlg status 200");
        var frameResultFile = document.getElementById("uploadedResultFile").value;
        var frameImage = document.getElementById("uploadedImage").value;

        var dataTable = document.getElementById("DataTable");
        var tableString = "";
  
        // tableString += "</tbody>";
        // console.log(tableString);

        DataTableBody = document.getElementById("DataTableBody");
        DataTableBody.innerHTML = tableString;
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
    }
}

function setUploadedResultFile() {
    //var file = document.getElementById('uploadedResultFile').files[0];
    var file = "Bats result file"
    //set iframe
    const obj_url = URL.createObjectURL(file);
    const iframe = document.getElementById('uploadedResultFile');
    iframe.setAttribute('src', obj_url);
    URL.revokeObjectURL(obj_url);

    const reader = new FileReader();
    reader.readAsText(file);
    reader.onload = function(evt) {
        console.log("in reader.onload");
        console.log(evt.target.result);
        console.log("reader.result: ");
        console.log(reader.result);
        content = reader.result.toString();
        console.log("in setContent content: "+ reader.result);
        console.log("in setContent content.toString(): " + content);
    };
}

function setUploadedImage() {
    //var file = document.getElementById('uploadedImage').files[0];
    var file = "Bats image"
    //set iframe
    const obj_url = URL.createObjectURL(file);
    const iframe = document.getElementById('uploadedImage');
    iframe.setAttribute('src', obj_url);
    URL.revokeObjectURL(obj_url);

    const reader = new FileReader();
    reader.readAsText(file);
    reader.onload = function(evt) {
        console.log("in reader.onload");
        console.log(evt.target.result);
        console.log("reader.result: ");
        console.log(reader.result);
        content = reader.result.toString();
        console.log("in setContent content: "+ reader.result);
        console.log("in setContent content.toString(): " + content);
    };
}



////////////////New Tool PAGE/////////////////////////////////////////

function setToolContent() {
    var file = document.getElementById('inputToolFile').files[0];
    //set iframe
    const obj_url = URL.createObjectURL(document.getElementById('inputToolFile').files[0]);
    const iframe = document.getElementById('uploadedToolViewer');
    iframe.setAttribute('src', obj_url);
    URL.revokeObjectURL(obj_url);

    const reader = new FileReader();
    reader.readAsText(file);
    reader.onload = function(evt) {
        console.log("in reader.onload");
        console.log(evt.target.result);
        console.log("reader.result: ");
        console.log(reader.result);
        content = reader.result.toString();
        console.log("in setContent content: "+ reader.result);
        console.log("in setContent content.toString(): " + content);
    };
}

function setDataContent() {
    var file = document.getElementById('dataInputToolFile').files[0];
    //set iframe
    const obj_url = URL.createObjectURL(document.getElementById('dataInputToolFile').files[0]);
    const iframe = document.getElementById('uploadedDataViewer');
    iframe.setAttribute('src', obj_url);
    URL.revokeObjectURL(obj_url);

    const reader = new FileReader();
    reader.readAsText(file);
    reader.onload = function(evt) {
        console.log("in reader.onload");
        console.log(evt.target.result);
        console.log("reader.result: ");
        console.log(reader.result);
        content = reader.result.toString();
        console.log("in setContent content: "+ reader.result);
        console.log("in setContent content.toString(): " + content);
    };
}

function checkNewTool() {
    var fn = document.getElementById("inputToolFile").value;
    var n = document.getElementById("inputToolName").value;
    var isFacialData = document.getElementById("facialData").checked;
    var isNeuralData = document.getElementById("neuralData").checked;
    var isSpeechData = document.getElementById("speechData").checked;
    var isSiData = document.getElementById("siData").checked;
    var isLogData = document.getElementById("logData").checked;

    var fn2 = fn.split("\\");
    var fn3 = fn2[fn2.length-1].split(".");

    console.log("filename: " + fn);
    console.log("fn2: " + fn2);
    console.log("fn3: " + fn3);
    console.log("fn3[0]: " + fn3[0]);
    console.log("fn3[1]: " + fn3[1]);
    console.log("name: " + n);

    // const iframe = document.getElementById('uploadedToolViewer');
    // const obj_url = URL.createObjectURL(document.getElementById('inputToolFile').files[0]);
    // console.log("iframe.value: " + iframe.value);
    // console.log("iframe.innerHTML: " + iframe.innerHTML);
    // console.log("iframe.src: " + iframe.src);
    // console.log("obj_url: " + obj_url);

    // var file = document.getElementById('inputToolFile').files[0];
    // var content;
    // const reader = new FileReader();
    // reader.readAsText(file);
    // reader.onload = function(evt) {
    //     console.log("in reader.onload");
    //     console.log(evt.target.result);
    //     console.log("reader.result: ");
    //     console.log(reader.result);
    //     content = reader.result;
    // };
    

    if (fn == "") {
        alert("Please upload a file before continuing");
        return false;
    }

    if (n == "") {
        alert("Please enter a name for the analysis tool before continuing");
        return false;
    }

    var selDataType = "";
    if(isFacialData){
        selDataType += "Facial";
    }
    if(isNeuralData){
        if(selDataType != ""){
            selDataType += ",";
        }
        selDataType += "Neural";
    }
    if(isSpeechData){
        if(selDataType != ""){
            selDataType += ",";
        }
        selDataType += "Speech";
    }
    if(isSiData){
        if(selDataType != ""){
            selDataType += ",";
        }
        selDataType += "Stress Indicators";
    }
    if(isLogData){
        if(selDataType != ""){
            selDataType += ",";
        }
        selDataType += "Log Data";
    }

    console.log("In checkNewTool selDataType: " + selDataType);

    var json = {file: content, filename: fn3[0], name: n, dataType: selDataType, ext: fn3[1], studyId: localStorage.getItem("currentStudyId"), authUserId: localStorage.getItem("currentAuthUserId")};

    var js = JSON.stringify(json);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", NewTool_url, true);

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
            processNewToolResponse(xhr.responseText);
        } else {
            console.log("got an error");
            processNewToolResponse("N/A");
        }
    };

    return false;
}

function processNewToolResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var tool = js["tool"];

    if (status == 200) {
        // window.location.href = "studyPage.html";
        window.location.href = localStorage.getItem("beforeUpload") + ".html";
    } else {
        var msg = js["error"];
        console.log("error:" + msg);

        var textString = "<p> error: " + msg + "</p>";
        // loginText = document.getElementById("loginText");
        // loginText.innerHTML = textString;
    }
}

//NEWDATA PAGE

function checkNewData() {
    var fn = document.getElementById("dataInputToolFile").value;
    var n = document.getElementById("dataInputToolName").value;
    var isFacialData = document.getElementById("newFacialData").checked;
    var isNeuralData = document.getElementById("newNeuralData").checked;
    var isSpeechData = document.getElementById("newSpeechData").checked;
    var isSiData = document.getElementById("newSiData").checked;
    var isLogData = document.getElementById("newLogData").checked;

    var fn2 = fn.split("\\");
    var fn3 = fn2[fn2.length-1].split(".");

    console.log("filename: " + fn);
    console.log("fn2: " + fn2);
    console.log("fn3: " + fn3);
    console.log("fn3[0]: " + fn3[0]);
    console.log("fn3[1]: " + fn3[1]);
    console.log("name: " + n);    

    if (fn == "") {
        alert("Please upload a file before continuing");
        return false;
    }

    if (n == "") {
        alert("Please enter a name for the analysis tool before continuing");
        return false;
    }

    var selDataType = "";
    if(isFacialData){
        selDataType += "Facial";
    }
    if(isNeuralData){
        if(selDataType != ""){
            selDataType += ",";
        }
        selDataType += "Neural";
    }
    if(isSpeechData){
        if(selDataType != ""){
            selDataType += ",";
        }
        selDataType += "Speech";
    }
    if(isSiData){
        if(selDataType != ""){
            selDataType += ",";
        }
        selDataType += "Stress Indicators";
    }
    if(isLogData){
        if(selDataType != ""){
            selDataType += ",";
        }
        selDataType += "Log Data";
    }

    console.log("In checkNewData selDataType: " + selDataType);

    var json = {file: content, filename: fn3[0], name: n, dataType: selDataType, ext: fn3[1], studyId: localStorage.getItem("currentStudyId"), authUserId: localStorage.getItem("currentAuthUserId")};

    var js = JSON.stringify(json);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", NewData_url, true);

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
            processNewDataResponse(xhr.responseText);
        } else {
            console.log("got an error");
            processNewDataResponse("N/A");
        }
    };

    return false;
}

function processNewDataResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var data = js["data"];

    if (status == 200) {
        // window.location.href = "studyPage.html";
        window.location.href = localStorage.getItem("beforeUpload") + ".html";
    } else {
        var msg = js["error"];
        console.log("error:" + msg);

        var textString = "<p> error: " + msg + "</p>";
        loginText = document.getElementById("loginText");
        loginText.innerHTML = textString;
    }
}

function getStudyDataForStudyPage() {
    var json = {studyId: localStorage.getItem('currentStudyId')};

    var js = JSON.stringify(json);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", GetStudyData_url, true);

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
            processGetStudyDataResponse2(xhr.responseText);
        } else {
            console.log("got an error");
            processGetStudyDataResponse2("N/A");
        }
    };

    return false;
}

function processGetStudyDataResponse2(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var data = js["data"];

    if (status == 200) {
        console.log("getStudyData status 200");

        var tableString = "";
        var doc;
  
        for(let i = 0; i < data.length; i++){
                doc = data[i];
                //create row to be inserted
                tableString += "<tr>";

                tableString += "<td>";
                tableString += doc["name"];
                tableString += "</td>";

                tableString += "<td>";
                tableString += doc["filename"];
                tableString += "</td>";

                tableString += "<td>";
                tableString += doc["ext"];
                tableString += "</td>";

                tableString += "<td>";
                tableString += doc["docType"];
                tableString += "</td>";

                tableString += "</tr>";                    
        }
        // tableString += "</tbody>";
        // console.log(tableString);

        DataTableBody = document.getElementById("studyPageDataTableBody");
        DataTableBody.innerHTML = tableString;
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
    }
}

function getStudyToolsForStudyPage() {
    var json = {studyId: localStorage.getItem('currentStudyId')};

    var js = JSON.stringify(json);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", GetStudyTools_url, true);

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
            processGetStudyDataResponse3(xhr.responseText);
        } else {
            console.log("got an error");
            processGetStudyDataResponse3("N/A");
        }
    };

    return false;
}

function processGetStudyDataResponse3(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var tools = js["tools"];

    if (status == 200) {
        console.log("getStudyData status 200");

        var tableString = "";
        var tool;
  
        for(let i = 0; i < tools.length; i++){
                tool = tools[i];
                //create row to be inserted
                tableString += "<tr>";

                tableString += "<td>";
                tableString += tool["name"];
                tableString += "</td>";

                tableString += "<td>";
                tableString += tool["filename"];
                tableString += "</td>";

                tableString += "<td>";
                tableString += tool["ext"];
                tableString += "</td>";

                tableString += "<td>";
                tableString += tool["docType"];
                tableString += "</td>";

                tableString += "</tr>";                    
        }
        // tableString += "</tbody>";
        // console.log(tableString);

        DataTableBody = document.getElementById("studyPageToolTableBody");
        DataTableBody.innerHTML = tableString;
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
    }
}

function setSelectedDataAndTool() {
    var tableString = "";

    tableString += "<tr>";

    tableString += "<td>";
    tableString += localStorage.getItem("currentDataName");
    tableString += "</td>";

    tableString += "<td>";
    tableString += localStorage.getItem("currentDataFilename");
    tableString += "</td>";

    tableString += "</tr>";

    DataTableBody = document.getElementById("algoResultDataTableBody");
    DataTableBody.innerHTML = tableString;

    tableString = "";

    tableString += "<tr>";

    tableString += "<td>";
    tableString += localStorage.getItem("currentToolName");
    tableString += "</td>";

    tableString += "<td>";
    tableString += localStorage.getItem("algName");
    tableString += "</td>";

    tableString += "</tr>";

    toolTableBody = document.getElementById("algoResultToolTableBody");
    toolTableBody.innerHTML = tableString;
}

//Study Info Page
function checkGetStudyInfo() {
    var json = {studyId: localStorage.getItem('currentStudyId')};

    var js = JSON.stringify(json);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", GetStudyInfo_url, true);

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
            processGetStudyInfoResponse(xhr.responseText);
        } else {
            console.log("got an error");
            processGetStudyInfoResponse("N/A");
        }
    };

    return false;
}

function processGetStudyInfoResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var study = js["study"];

    if (status == 200) {
        console.log("getData status 200");

        studyInfoStudyName = document.getElementById("studyInfoStudyName");
        studyInfoStudyName.innerHTML = study["studyName"];

        studyInfoShortStudyName = document.getElementById("studyInfoShortStudyName");
        studyInfoShortStudyName.innerHTML = study["studyShortName"];

        studyInfoStudyAbstract = document.getElementById("studyInfoStudyAbstract");
        studyInfoStudyAbstract.innerHTML = study["studyDescription"];

        studyInfoInstitutionsInvolved = document.getElementById("studyInfoInstitutionsInvolved");
        studyInfoInstitutionsInvolved.innerHTML = study["institutionsInvolved"];

        studyInfoStudyContact = document.getElementById("studyInfoStudyContact");
        studyInfoStudyContact.innerHTML = study["studyContact"];

        studyInfoStudyNotes = document.getElementById("studyInfoStudyNotes");
        studyInfoStudyNotes.innerHTML = study["studyNotes"];

        studyInfoIsIrbApproved = document.getElementById("studyInfoIsIrbApproved");
        studyInfoIsIrbApproved.innerHTML = study["isIrbApproved"];

        studyInfoVisibility = document.getElementById("studyInfoVisibility");
        studyInfoVisibility.innerHTML = study["visibility"];

        studyInfoStartDate = document.getElementById("studyInfoStartDate");
        studyInfoStartDate.innerHTML = study["studyStartDate"];

        studyInfoEndDate = document.getElementById("studyInfoEndDate");
        studyInfoEndDate.innerHTML = study["studyEndDate"];

        studyInfoLastMod = document.getElementById("studyInfoLastMod");
        studyInfoLastMod.innerHTML = study["lastMod"];

        studyInfoUsername = document.getElementById("studyInfoUsername");
        studyInfoUsername.innerHTML = study["authUserId"];
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
    }
}

//Edit Study Page
function loadEditStudyPage() {
    var json = {studyId: localStorage.getItem('currentStudyId')};

    var js = JSON.stringify(json);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", GetStudyInfo_url, true);

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
            processGetStudyInfoResponse2(xhr.responseText);
        } else {
            console.log("got an error");
            processGetStudyInfoResponse2("N/A");
        }
    };

    return false;
}

function processGetStudyInfoResponse2(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var study = js["study"];

    if (status == 200) {
        console.log("getData status 200");

        editInputStudyName = document.getElementById("editInputStudyName");
        editInputStudyName.value = study["studyName"];

        editInputShortStudyName = document.getElementById("editInputShortStudyName");
        editInputShortStudyName.value = study["studyShortName"];

        editInputStudyAbstract = document.getElementById("editInputStudyAbstract");
        editInputStudyAbstract.value = study["studyDescription"];

        editInputStudyInstitutionsInvolved = document.getElementById("editInputStudyInstitutionsInvolved");
        editInputStudyInstitutionsInvolved.value = study["institutionsInvolved"];

        editInputStudyStudyContact = document.getElementById("editInputStudyStudyContact");
        editInputStudyStudyContact.value = study["studyContact"];

        editInputStudyStudyNotes = document.getElementById("editInputStudyStudyNotes");
        editInputStudyStudyNotes.value = study["studyNotes"];

        if(study["isIrbApproved"] == "yes"){
            editInputStudyIsIrbApprovedYes = document.getElementById("editInputStudyIsIrbApprovedYes");
            editInputStudyIsIrbApprovedYes.checked = true;

            editInputStudyIsIrbApprovedNo = document.getElementById("editInputStudyIsIrbApprovedNo");
            editInputStudyIsIrbApprovedNo.checked = false;
        } else{
            editInputStudyIsIrbApprovedYes = document.getElementById("editInputStudyIsIrbApprovedYes");
            editInputStudyIsIrbApprovedYes.checked = false;

            editInputStudyIsIrbApprovedNo = document.getElementById("editInputStudyIsIrbApprovedNo");
            editInputStudyIsIrbApprovedNo.checked = true;
        }

        if(study["visibility"] == "yes"){
            editInputStudyVisibilityYes = document.getElementById("editInputStudyVisibilityYes");
            editInputStudyVisibilityYes.checked = true;

            editInputStudyVisibilityNo = document.getElementById("editInputStudyVisibilityNo");
            editInputStudyVisibilityNo.checked = false;
        } else{
            editInputStudyVisibilityYes = document.getElementById("editInputStudyVisibilityYes");
            editInputStudyVisibilityYes.checked = false;

            editInputStudyVisibilityNo = document.getElementById("editInputStudyVisibilityNo");
            editInputStudyVisibilityNo.checked = true;
        }
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
    }
}

function checkEditStudyInfo() {
    var editInputStudyName = document.getElementById("editInputStudyName").value;
    var editInputShortStudyName = document.getElementById("editInputShortStudyName").value;
    var editInputStudyAbstract = document.getElementById("editInputStudyAbstract").value;
    var editInputStudyInstitutionsInvolved = document.getElementById("editInputStudyInstitutionsInvolved").value;
    var editInputStudyStudyContact = document.getElementById("editInputStudyStudyContact").value;
    var editInputStudyStudyNotes = document.getElementById("editInputStudyStudyNotes").value;
    var editInputStudyIsIrbApprovedYes = document.getElementById("editInputStudyIsIrbApprovedYes").checked;
    var editInputStudyIsIrbApprovedNo = document.getElementById("editInputStudyIsIrbApprovedNo").checked;
    var editInputStudyVisibilityYes = document.getElementById("editInputStudyVisibilityYes").checked;
    var editInputStudyVisibilityNo = document.getElementById("editInputStudyVisibilityNo").checked;

    if (editInputStudyName == "") {
        alert("Please enter a study name before continuing");
        return false;
    }

    var editInputStudyIsIrbApproved = "";
    if(editInputStudyIsIrbApprovedYes){
        editInputStudyIsIrbApproved = "yes";
    } else if(editInputStudyIsIrbApprovedNo){
        editInputStudyIsIrbApproved = "no";
    }

    var editInputStudyVisibility = "";
    if(editInputStudyVisibilityYes){
        editInputStudyVisibility = "yes";
    } else if(editInputStudyVisibilityNo){
        editInputStudyVisibility = "no";
    }

    // console.log("currentAuthUserId in checkNewStudy: " + localStorage.getItem('currentAuthUserId'));
    console.log("editInputStudyIsIrbApprovedYes:" + editInputStudyIsIrbApprovedYes);
    console.log("editInputStudyIsIrbApprovedNo:" + editInputStudyIsIrbApprovedNo);
    console.log("editInputStudyVisibilityYes:" + editInputStudyVisibilityYes);
    console.log("editInputStudyVisibilityNo:" + editInputStudyVisibilityNo);

    var json = {studyId: localStorage.getItem("currentStudyId"), studyName: editInputStudyName, studyShortName: editInputShortStudyName, studyDescription: editInputStudyAbstract, institutionsInvolved: editInputStudyInstitutionsInvolved, studyContact: editInputStudyStudyContact, studyNotes: editInputStudyStudyNotes, isIrbApproved: editInputStudyIsIrbApproved, visibility: editInputStudyVisibility};

    var js = JSON.stringify(json);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", EditStudyInfo_url, true);

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
            processEditStudyInfoResponse(xhr.responseText);
        } else {
            console.log("got an error");
            processEditStudyInfoResponse("N/A");
        }
    };

    return false;
}

function processEditStudyInfoResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];

    if (status == 200) {
        window.location.href = "studyInfo.html";
    } else {
        var msg = js["error"];
        console.log("error:" + msg);

        var textString = "<p> error: " + msg + "</p>";
        newStudyText = document.getElementById("newStudyText");
        renewStudyTextgText.innerHTML = textString;
    }
}