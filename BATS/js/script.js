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
    localStorage.setItem("currentDataDocumentId", "");
    document.getElementById("studyNameOnStudyPage").innerHTML = localStorage.getItem("studyName");
    localStorage.setItem("currentCutoff", "0.3");
    localStorage.setItem("currentOrder", "1");
}

function loadSelectData(){
    localStorage.setItem("beforeUpload", "selectData");
    localStorage.setItem("currentDataDocumentId", "");
}

function loadOptions(){
    localStorage.setItem("beforeUpload", "options");
    var title = "Enter " + localStorage.getItem("currentToolName") + " Options";
    document.getElementById("optionsTitle").innerHTML = title;
    localStorage.setItem("currentCutoff", "0.3");
    localStorage.setItem("currentOrder", "1");
}

function loadSelectAlgorithms(){
    localStorage.setItem("beforeUpload", "selectAlgorithms");
    localStorage.setItem("selectAlgsTableString", document.getElementById("selectAlgsToolkits"));
}

function checkLogin() {
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

    var user = document.getElementById("loginUsername").value;
    var pass = document.getElementById("loginPassword").value;

    if (user == "") {
        alert("Please enter a username before continuing");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    if (pass == "") {
        alert("Please enter a password before continuing");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
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
        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        window.location.href = "userHome.html";
        // window.onload = checkUsername(username);
    } else {
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        var msg = js["error"];
        console.log("error:" + msg);

        var textString = "<p>" + msg + "</p>";
        loginText = document.getElementById("loginText");
        loginText.innerHTML = textString;
    }
}

////////////////REGISTER PAGE/////////////////////////////////////////

function checkRegister() {
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

    var user = document.getElementById("regUsername").value;
    var eMail = document.getElementById("regEmail").value;
    var pass = document.getElementById("regPassword").value;
    var pass2 = document.getElementById("regPasswordConfirm").value;

    if (user == "") {
        alert("Please enter a username before continuing");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    if (eMail == "") {
        alert("Please enter an email before continuing");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    if (pass == "") {
        alert("Please enter a password before continuing");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    if (pass2 == "") {
        alert("Please confirm your password before continuing");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    if (pass != pass2) {
        alert("The passwords entered are not the same");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
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
        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
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
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        var msg = js["error"];
        console.log("error:" + msg);

        var textString = "<p>" + msg + "</p>";
        regText = document.getElementById("regText");
        regText.innerHTML = textString;
    }
}

////////////////NEW STUDY PAGE/////////////////////////////////////////

function checkNewStudy() {
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

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
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    if (inputStudyAbstract.length > 500) {
        alert("The entered abstract exceeds the 500 character limit.");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
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
        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        window.location.href = "userHome.html";
    } else {
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        var msg = js["error"];
        console.log("error:" + msg);

        // var textString = "<p> error: " + msg + "</p>";
        // newStudyText = document.getElementById("newStudyText");
        // renewStudyTextgText.innerHTML = textString;
        alert(msg);
    }
}

////////////////USER HOME PAGE/////////////////////////////////////////

async function checkGetUsername(aid) {
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

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
        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");

        r = username;
        localStorage.setItem("usernameOfAU", username);
        console.log("in processGetUsernameResponse r: " + r);
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        alert(msg);
    }
    return r;
}

function checkGetStudies() {
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

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

// const processGetStudiesResponse = async () => {
async function processGetStudiesResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var studies = js["studies"];

    if (status == 200) {
        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");

        console.log("getStudies status 200");

        var studyTable = document.getElementById("tableOfStudiesOnUserHome");
        var study;
        var username;

        var tableString = "";
  
        for(let i = 0; i < studies.length; i++){
            study = studies[i];
            username = await checkGetUsername(study["authUserId"]);
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
                tableString += "<td class='abstractTd'>";

                tableString += study["studyDescription"];               
                // tableString += '<div class="accordion" id="abstractACD"><div class="card"><div class="card-header" id="headingOne"><h2 class="mb-0"><button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">Show Abstract</button></h2></div><div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#abstractACD"><div class="card-body">';
                // tableString += study["studyDescription"];
                // tableString += '</div></div></div></div>';
                
                tableString += "</td>";
                tableString += "<td>";
                tableString += localStorage.getItem("currentUser");
                tableString += "</td>";
                tableString += "<td>";
                tableString += study["studyContact"];
                tableString += "</td>";
                tableString += "<td>";
                tableString += study["institutionsInvolved"];
                tableString += "</td>";
                // tableString += "<td>";
                // tableString += study["studyNotes"];
                // tableString += "</td>";
                tableString += "<td>";
                tableString += study["isIrbApproved"];
                tableString += "</td>";
                tableString += "<td>";
                tableString += study["visibility"];
                tableString += "</td>";
                // tableString += "<td>";
                // tableString += "date";
                // tableString += "</td>";
                tableString += "</tr>";                    
        }
        // tableString += "</tbody>";
        // console.log(tableString);

        studyTableTbody = document.getElementById("studyTableTbody");
        studyTableTbody.innerHTML = tableString;
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        alert(msg);
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
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

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

    // console.log("studies: " + JSON.stringify(studies));

    if (status == 200) {
        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");

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

                // tableString += "<td>";
                // // tableString += studies[i]["studyName"];
                // tableString += "Study Name"
                // tableString += "</td>";

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
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        alert(msg);
    }
}
////////////////Select Data Page////////////////////////////////////////////////////////////////////

function checkGetStudyData() {
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

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
        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        
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
                tableString += doc["ext"];
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
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        alert(msg);
    }
}

function dataClicked(documentId, authUserId, filename, name, ext, chosenI) {
    if (((localStorage.getItem("currentAlgName") === "graphcsv") || (localStorage.getItem("currentAlgName") === "highpass") || (localStorage.getItem("currentAlgName") === "lowpass") || (localStorage.getItem("currentAlgName") === "ttest")) &&
        ext != "csv") {
        alert("Only .csv data can be " + localStorage.getItem("currentToolName") + " graphed. Please select .csv data before continuing.");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    if ((localStorage.getItem("currentAlgName") === "graphwav") &&
        ext != "wav") {
        alert("Only .wav data can be " + localStorage.getItem("currentToolName") + " graphed. Please select .wav data before continuing.");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    console.log("in dataClicked");
    console.log("documentId: " + documentId);
    console.log("authUserId: " + authUserId);
    console.log("filename (in dataClicked): " + filename);
    console.log("name (in dataClicked): " + name);
    localStorage.setItem('currentDataDocumentId', documentId);
    // localStorage.setItem('currentAuthUserId', authUserId);
    localStorage.setItem('currentDataFilename', filename);
    localStorage.setItem('currentDataName', name);
    localStorage.setItem("currentDataExt", ext);
    // localStorage.setItem('currentDatai', i);
    currentFileName = localStorage.getItem('filename');
    currentName = localStorage.getItem('name');
    currentDataDocumentId = localStorage.getItem('currentDataDocumentId');
    currentDataExt = localStorage.getItem("currentDataExt");
    console.log("currentFileName (in dataClicked): " + currentFileName);
    console.log("currentName (in dataClicked): " + currentName);
    console.log("currentDataDocumentId (in dataClicked): " + currentDataDocumentId);
    console.log("currentDataExt (in dataClicked): " + currentDataExt);
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
    splitter += ext;
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
    localStorage.setItem('currentAlgName', algName);
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
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

    console.log("localStorage.getItem('currentAlgName'): " + localStorage.getItem('currentAlgName'));

    var json = {dataDocumentId: localStorage.getItem('currentDataDocumentId'), toolDocumentId: "", algName: localStorage.getItem('currentAlgName'), studyId: localStorage.getItem("currentStudyId"), cutoff: parseFloat(localStorage.getItem("currentCutoff")), order: parseFloat(localStorage.getItem("currentOrder"))};

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
        setUploadedResultFile(resultFile);
        // setUploadedImage(image);

        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");

        // var frameResultFile = document.getElementById("uploadedResultFile").value;
        // var frameImage = document.getElementById("uploadedImage").value;
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        alert(msg);
    }
}

function setUploadedResultFile(resultFile) {
    var resultFileForIframe;
    if((localStorage.getItem("currentAlgName") === "graphcsv") || (localStorage.getItem("currentAlgName") === "highpass") || (localStorage.getItem("currentAlgName") === "lowpass")){
        resultFileForIframe = "Time (seconds), Wavelength(meters)\n" + resultFile;
    } else{
        resultFileForIframe = resultFile;
    }
    resultFile = "sec,met\n" + resultFile;
    //var file = document.getElementById('uploadedResultFile').files[0];
    // const blob = new Blob([resultFile], {type : 'text/plain'});
    var file = new File([resultFileForIframe], "resultFile.csv", {
        type: "text/plain",
      });
      var fileCSV = new File([resultFile], "resultFile.csv", {
        type: "text/csv",
      });
    // var file = "Bats result file"
    //set iframe
    const obj_url = URL.createObjectURL(file);
    const obj_url2 = URL.createObjectURL(fileCSV);
    const iframe = document.getElementById('uploadedResultFile');
    iframe.setAttribute('src', obj_url);
    //set download href
    document.getElementById("downloadResultFile").setAttribute("href", obj_url2);
    
    // URL.revokeObjectURL(obj_url);

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

    if(localStorage.getItem("currentAlgName") != "ttest"){
        createGraph2(obj_url2);
    }
    
}

function csv2json(csv){
      
    var nested = d3.nest()
        .key(function(d){ return d.sec; })
      .entries(csv)
        
    var json = nested.map(function(d){
      var timerecord = {};
      
      // this is the variable that we grouped by
      timerecord.sec = d.key; 

      timerecord.met = d.values[0].met;
      
      return timerecord;
    });
    
    return json;
  }

function createGraph2(obj_url2) {
                 
    d3.csv(obj_url2, function(csv){
    
        csv.forEach(function(d){
            d.met = +d.met;
        });
                    
        console.table(csv);
        
        var json = csv2json(csv);

        // data = json;

        console.log("In d3.csv: ");
        // console.log("data: ");
        // console.log(data);
        
        console.log("json: ");

        console.log(json);
        
        var data = json;

        console.log("In createGraph2 after got json -> data");
        
        console.log("After d3.csv: ");
        console.log("data: ");
        console.log(data);
        // console.log("json: ");
        // console.log(json);

        var margin = {
            top: 40,
            right: 20,
            bottom: 60,
            left: 60
        }
        width = 700 - margin.left - margin.right;
        height = 500 - margin.top - margin.bottom;

        var x = d3.scaleLinear().range([0, width]);
        // var y = d3.scaleLinear().range([height, 0]);
        // Scale the range of the data
        x.domain(d3.extent(data, function (d) {
            return d.sec;
        }));
        // y.domain([0, d3.max(data, function (d) {
        //     return d.met;
        // })]);

        y = d3.scaleLinear()
        .domain(d3.extent(data, d => d.met)).nice()
        .range([height, 0])
        // .range([height - margin.bottom, margin.top])

        var valueline = d3.line()
        .x(function (d) {
            return x(d.sec);
        })
        .y(function (d) {
            return y(d.met);
        });

        var svg = d3.select("#scatter").append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

        var path = svg.selectAll("dot")
        .data(data)
        .enter().append("circle")
        .attr("r", 5)
        .attr("cx", function (d) {
            return x(d.sec);
        })
        .attr("cy", function (d) {
            return y(d.met);
        })
        .attr("stroke", "#32CD32")
        .attr("stroke-width", 1.5)
        .attr("fill", "#FFFFFF");

        svg.append("g")
        .attr("transform", "translate(0," + height + ")")
        .call(d3.axisBottom(x));

        svg.append("g")
        .call(d3.axisLeft(y));

        // text label for the x axis
        svg.append("text")             
        .attr("transform",
                "translate(" + (width/2) + " ," + 
                            (height + margin.top + 20) + ")")
        .style("text-anchor", "middle")
        .text("Time (seconds)");

        // text label for the y axis
        svg.append("text")
        .attr("transform", "rotate(-90)")
        .attr("y", 0 - margin.left)
        .attr("x",0 - (height / 2))
        .attr("dy", "1em")
        .style("text-anchor", "middle")
        .text("Wavelength (meters)");

        svg.append("text")
        .attr("x", (width / 2))             
        .attr("y", 0 - (margin.top / 2))
        .attr("text-anchor", "middle")  
        .style("font-size", "16px") 
        .style("text-decoration", "bold")  
        .text(localStorage.getItem("currentDataFilename") + " " + localStorage.getItem("currentToolName"));
    
        // gridlines in x axis function
        function make_x_gridlines() {		
            return d3.axisBottom(x)
        }

        // gridlines in y axis function
        function make_y_gridlines() {		
            return d3.axisLeft(y)
        }

        // add the X gridlines
        svg.append("g")			
        .attr("class", "grid")
        .attr("transform", "translate(0," + height + ")")
        .call(make_x_gridlines()
            .tickSize(-height)
            .tickFormat("")
        )

        // add the Y gridlines
        svg.append("g")			
        .attr("class", "grid")
        .call(make_y_gridlines()
            .tickSize(-width)
            .tickFormat("")
        )


        d3.select("#downloadGraph")
        .on('click', function(){
            // Get the d3js SVG element and save using saveSvgAsPng.js
            saveSvgAsPng(document.getElementsByTagName("svg")[0], (localStorage.getItem("currentDataFilename") + " " + localStorage.getItem("currentToolName")+ ".png"));
        })

    })





//     console.log("After d3.csv: ");
//     console.log("data: ");
//     console.log(data);
//     // console.log("json: ");
//     // console.log(json);

//     var margin = {
//         top: 20,
//         right: 20,
//         bottom: 30,
//         left: 40
//    }
//    width = 700 - margin.left - margin.right;
//    height = 500 - margin.top - margin.bottom;

//    var x = d3.scaleLinear().range([0, width]);
//     var y = d3.scaleLinear().range([height, 0]);
//     // Scale the range of the data
//     x.domain(d3.extent(data, function (d) {
//         return d.sec;
//     }));
//     y.domain([0, d3.max(data, function (d) {
//         return d.met;
//     })]);

//     var valueline = d3.line()
//      .x(function (d) {
//           return x(d.sec);
//      })
//      .y(function (d) {
//           return y(d.met);
//      });

//      var svg = d3.select("#scatter").append("svg")
//      .attr("width", width + margin.left + margin.right)
//      .attr("height", height + margin.top + margin.bottom)
//      .append("g")
//      .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

//      var path = svg.selectAll("dot")
//      .data(data)
//      .enter().append("circle")
//      .attr("r", 5)
//      .attr("cx", function (d) {
//            return x(d.sec);
//      })
//      .attr("cy", function (d) {
//           return y(d.met);
//      })
//      .attr("stroke", "#32CD32")
//      .attr("stroke-width", 1.5)
//      .attr("fill", "#FFFFFF");

//      svg.append("g")
//      .attr("transform", "translate(0," + height + ")")
//      .call(d3.axisBottom(x));

//      svg.append("g")
//      .call(d3.axisLeft(y).ticks(0,5,50));
}

function setUploadedImage(image) {
    console.log("image in setUploadedImage: " + image);
    //var file = document.getElementById('uploadedImage').files[0];
    // const blob = new Blob([image], {type : 'text/plain'});
    var file = new File([image], "foo.txt", {
        type: "image/png",
      });
    // var file = "Bats image"
    //set iframe
    const obj_url = URL.createObjectURL(file);
    const img = document.getElementById('uploadedImage');
    var src = "data:image/png;base64," + image;
    // img.setAttribute('src', src);
    if(localStorage.getItem("algName") === "lowpass"){
        img.setAttribute('src', '../img/Test_Wave.csv_lowpassGraph.png');
    } else {
        img.setAttribute('src', '../img/Test_Wave.csv_Graph.png');
    }

    //set download href
    document.getElementById("downloadGraph").setAttribute("href", '../img/Test_Wave.csv_Graph.png');

    // var canvas = document.createElement("canvas");
    // canvas.width = 200;
    // canvas.height = 200;
    // canvas.setAttribute('src', obj_url);

    URL.revokeObjectURL(obj_url);

    // var canvas = document.createElement("canvas");
    // canvas.width = 200;
    // canvas.height = 200;
    // var url = canvas.toDataURL();
    // var a = document.createElement('a');
    // a.download = 'my.png';
    // a.href = url;
    // a.textContent = 'Download PNG';
    // document.body.appendChild(a);

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
    // const obj_url = URL.createObjectURL(file);
    // var iframe = document.getElementById('uploadedDataViewer');
    // iframe.setAttribute('src', obj_url);
    // URL.revokeObjectURL(obj_url);

    var reader = new FileReader();
    var file2;
    reader.onload = function(e) {
        // The file's text will be printed here
        console.log(e.target.result);
        content = reader.result.toString();
        localStorage.setItem("newDataContent", content);
        console.log("localStorage.getItem('newDataContent'): " + localStorage.getItem('newDataContent'));
    };

    var fileContent = reader.readAsText(file);

    // const obj_url = URL.createObjectURL(file2);
    // const iframe = document.getElementById('uploadedDataViewer');
    // iframe.setAttribute('src', obj_url);
    // URL.revokeObjectURL(obj_url);

    // const reader = new FileReader();
    // reader.readAsText(file);
    // reader.onload = function(evt) {
    //     console.log("in reader.onload");
    //     console.log(evt.target.result);
    //     console.log("reader.result: ");
    //     console.log(reader.result);
    //     // content = reader.result.toString();
    //     // localStorage.setItem("newDataContent", content);
    //     console.log("in setContent content: "+ reader.result);
    //     console.log("in setContent content.toString(): " + content);
    // };
}

function checkNewTool() {
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

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
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    if (n == "") {
        alert("Please enter a name for the analysis tool before continuing");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
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
        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        // window.location.href = "studyPage.html";
        window.location.href = localStorage.getItem("beforeUpload") + ".html";
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        alert(msg);

        // var textString = "<p> error: " + msg + "</p>";
        // loginText = document.getElementById("loginText");
        // loginText.innerHTML = textString;
    }
}

//NEWDATA PAGE

function getBase64(file) {
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      console.log(reader.result);
      return reader.result;
    };
    reader.onerror = function (error) {
      console.log('Error: ', error);
      return error;
    };
 }

function checkNewData() {
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

    var fn = document.getElementById("dataInputToolFile").value;
    var n = document.getElementById("dataInputToolName").value;
    var isNeuralData = document.getElementById("newNeuralData").checked;
    var isSpeechData = document.getElementById("newSpeechData").checked;
    // var isSiData = document.getElementById("newSiData").checked;
    var isLogData = document.getElementById("newLogData").checked;
    var isOtherData = document.getElementById("newOtherData").checked;

    var fn2 = fn.split(".");
    var fn3 = fn2[0].split("\\");

    console.log("isNeuralData: " + isNeuralData);
    console.log("isSpeechData: " + isSpeechData);
    console.log("isLogData: " + isLogData);
    console.log("isOtherData: " + isOtherData); 

    if (fn == "") {
        alert("Please upload a file before continuing");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    if (n == "") {
        alert("Please enter a name for the analysis tool before continuing");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    var selDataType = "";

    if(isNeuralData){
        selDataType = "Neural";
    } else if(isSpeechData){
        selDataType = "Speech";
    } else if(isLogData){
        selDataType = "Log Data";
    } else if(isOtherData){
        selDataType = "Other";
    }

    if (selDataType == "") {
        alert("Please select a data type before continuing");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    // if(isFacialData){
    //     selDataType += "Facial";
    // }
    // if(isNeuralData){
    //     // if(selDataType != ""){
    //     //     selDataType += ",";
    //     // }
    //     selDataType = "Neural";
    // }
    // if(isSpeechData){
    //     // if(selDataType != ""){
    //     //     selDataType += ",";
    //     // }
    //     selDataType = "Speech";
    // }
    // // if(isSiData){
    // //     if(selDataType != ""){
    // //         selDataType += ",";
    // //     }
    // //     selDataType += "Stress Indicators";
    // // }
    // if(isLogData){
    //     // if(selDataType != ""){
    //     //     selDataType += ",";
    //     // }
    //     selDataType = "Log Data";
    // }

    console.log("In checkNewData selDataType: " + selDataType);

    var file = document.getElementById('dataInputToolFile').files[0];
    const reader = new FileReader();
    var fileAsText = reader.readAsText(file);
    console.log("in checkNewData fileAsText: " + fileAsText);
    // console.log("In checkNewData file: " + file.content);
    var fileBase64 = getBase64(file);
    console.log("In checkNewData fileBase64: " + fileBase64);

    var json = {file: localStorage.getItem("newDataContent"), filename: fn3[2], name: n, dataType: selDataType, ext: fn2[1], studyId: localStorage.getItem("currentStudyId"), authUserId: localStorage.getItem("currentAuthUserId")};

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
        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        // window.location.href = "studyPage.html";
        window.location.href = localStorage.getItem("beforeUpload") + ".html";
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        alert(msg);

        // var textString = "<p> error: " + msg + "</p>";
        // loginText = document.getElementById("loginText");
        // loginText.innerHTML = textString;
    }
}

function getStudyDataForStudyPage() {
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

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
        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");

        console.log("getStudyData status 200");

        var tableString = "";
        var doc;
        // var functionString = "";
  
        for(let i = 0; i < data.length; i++){
                doc = data[i];

                // functionString += "<td onclick='JavaScript:dataForGraphingClicked(";
                // functionString += '"';
                // functionString += doc["documentId"];
                // functionString += '", "';
                // functionString += doc["authUserId"];
                // functionString += '", "';
                // functionString += doc["filename"];
                // functionString += '", "';
                // functionString += doc["name"];
                // functionString += '", "';
                // functionString += doc["ext"];
                // functionString += '", "';
                // functionString += i;
                // functionString += '")';
                // functionString += "'>";

                // create row to be inserted
                // tableString += "<tr>";
                tableString += "<tr onclick='JavaScript:dataForGraphingClicked(";
                tableString += '"';
                tableString += doc["documentId"];
                tableString += '", "';
                tableString += doc["authUserId"];
                tableString += '", "';
                tableString += doc["filename"];
                tableString += '", "';
                tableString += doc["name"];
                tableString += '", "';
                tableString += doc["ext"];
                tableString += '", "';
                tableString += i;
                tableString += '")';
                tableString += "'>";

                // tableString += "<tr>"

                tableString += "<td>";
                // tableString += functionString;
                tableString += doc["name"];
                tableString += "</td>";

                tableString += "<td>";
                // tableString += functionString;
                tableString += doc["filename"];
                tableString += "</td>";

                tableString += "<td>";
                // tableString += functionString;
                tableString += doc["ext"];
                tableString += "</td>";

                tableString += "<td>";
                // tableString += functionString;
                tableString += doc["dataType"];
                tableString += "</td>";

                // tableString += "<td>";
                // tableString += "<a id='download";
                // tableString += doc["documentId"];
                // tableString += "role='button' download><img src='download.png'/></a>";
                // tableString += "</td>";

                tableString += "</tr>";
                functionString = "";                    
        }
        // for(let i = 0; i < data.length; i++){
        //     doc = data[i];
        //     checkGetDataDoc(doc.documentId);
        // }

        // tableString += "</tbody>";
        // console.log(tableString);

        DataTableBody = document.getElementById("studyPageDataTableBody");
        DataTableBody.innerHTML = tableString;
        algoClicked("", "graphcsv", "Graph CSV");
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        alert(msg);
    }
}

function getStudyToolsForStudyPage() {
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

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
        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        
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
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        alert(msg);
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
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

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

        // studyInfoLastMod = document.getElementById("studyInfoLastMod");
        // studyInfoLastMod.innerHTML = study["lastMod"];

        // studyInfoUsername = document.getElementById("studyInfoUsername");
        // studyInfoUsername.innerHTML = checkGetUsername(study["authUserId"]);

        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        alert(msg);
    }
}

//Edit Study Page
function loadEditStudyPage() {
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

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

        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        alert(msg);
    }
}

function checkEditStudyInfo() {
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

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
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
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
        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        window.location.href = "studyInfo.html";
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        alert(msg);

        // var textString = "<p> error: " + msg + "</p>";
        // newStudyText = document.getElementById("newStudyText");
        // renewStudyTextgText.innerHTML = textString;
    }
}

function dataForGraphingClicked(documentId, authUserId, filename, name, ext, chosenI) {
    if (ext != "csv") {
        alert("Only .csv data can be graphed. Please select .csv data before continuing.");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    console.log("in dataForGraphingClicked");
    console.log("documentId: " + documentId);
    console.log("authUserId: " + authUserId);
    console.log("filename (in dataClicked): " + filename);
    console.log("name (in dataClicked): " + name);
    localStorage.setItem('currentDataDocumentId', documentId);
    localStorage.setItem('currentDataFilename', filename);
    localStorage.setItem('currentDataName', name);
    localStorage.setItem('algName', "graphcsv");
    localStorage.setItem('currentAlgName', "graphcsv");
    window.location.href = "algoResult.html";
    checkRunAlg();
}

function goToBeforeUpload(){
    window.location.href = localStorage.getItem("beforeUpload") + ".html";
}

function runSelectedTool(algName, name) {
    localStorage.setItem("ranFromTool", "true");
    window.location.href = "selectData.html";
    algoClicked("", algName, name);
}

function analyzeDataButtonClicked() {
    localStorage.setItem("ranFromTool", "false");
    window.location.href='selectData.html';
    algoClicked("", "", "");
}

function nextFromSelectData() {
    if (localStorage.getItem("currentDataDocumentId") === "") {
        alert("Please select data before continuing");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    if((localStorage.getItem("currentAlgName") === "highpass") || (localStorage.getItem("currentAlgName") === "lowpass")){
        window.location.href='options.html';
    } else {
        var ranFromTool = localStorage.getItem("ranFromTool");
        if(ranFromTool === "true"){
            // localStorage.setItem("currentDataDocumentId", "");
            window.location.href='algoResult.html';
        } else if (ranFromTool === "false"){
            // localStorage.setItem("currentDataDocumentId", "");
            window.location.href='selectAlgorithms.html';
        }
    }
}

function nextFromOptions() {
    var ranFromTool = localStorage.getItem("ranFromTool");
    var cutoff = document.getElementById("inputCutoff").value;
    var order = document.getElementById("inputCutoff").value;

    if((cutoff < 0) || (cutoff >= 0.5)){
        alert("The cutoff frequency must be between 0 (inclusive) and 0.5 (exclusive). Please enter a new value.");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    if((order < 1) || (order > 5)){
        alert("The order must be between 1 (inclusive) and 5 (inclusive). Please enter a new value.");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    localStorage.setItem("currentCutoff", cutoff);
    localStorage.setItem("currentOrder", order);
    if(ranFromTool === "true"){
        // localStorage.setItem("currentDataDocumentId", "");
        window.location.href='algoResult.html';
    } else if (ranFromTool === "false"){
        // localStorage.setItem("currentDataDocumentId", "");
        window.location.href='selectAlgorithms.html';
    }
}

function addNewDataFromUserHome() {
    console.log("localStorage.getItem('studiesExist'): " + localStorage.getItem("studiesExist"));
    //alert if no studies for the current user
    if (localStorage.getItem("studiesExist") === "false") {
        alert("Please add a study before continuing");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    } else{
        localStorage.setItem("beforeUpload", "userHome");
        window.location.href = "uploadDataToStudy.html";
    }
}

function checkGetStudies2() {
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

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
            processGetStudiesResponse2(xhr.responseText);
        } else {
            console.log("got an error");
            processGetStudiesResponse2("N/A");
        }
    };

    return false;
}

function processGetStudiesResponse2(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var studies = js["studies"];

    if (status == 200) {
        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");

        console.log("getStudies status 200");

        var studyList = document.getElementById("studiesInUploadData");
        var study;
        var tableString = "";
        var id;
  
        for(let i = 0; i < studies.length; i++){
            study = studies[i];
            // checkGetUsername(study["authUserId"]);
            console.log("in processGetStudiesResponse usernameOfAU: " + localStorage.getItem("usernameOfAU"));

            id = "study" + i;
            tableString += '<div class="form-check"';
            tableString += " onclick='JavaScript:dataStudyClicked(";
            tableString += '"';
            tableString += study["studyId"];
            tableString += '", "';
            tableString += study["studyName"];
            tableString += '")';
            tableString+= "'>";
            tableString += '<input class="form-check-input" type="radio" name="';
            tableString += id;
            tableString += 'id="';
            tableString += id;
            tableString += '">';
            tableString += '<label class="form-check-label" for="';
            tableString += id;
            tableString += '">';
            tableString += study["studyName"];
            tableString += '</label></div>';
        }
        // tableString += "</tbody>";
        // console.log(tableString);

        // studyTableTbody = document.getElementById("studyTableTbody");
        studyList.innerHTML = tableString;
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        alert(msg);
    }
}

function dataStudyClicked(studyId, studyName) {
    localStorage.setItem("newDataForStudyId", studyId);
    localStorage.setItem("newDataForStudyName", studyName);
    console.log("newDataForStudyId: " + localStorage.getItem("newDataForStudyId"));
    console.log("newDataForStudyName: " + localStorage.getItem("newDataForStudyName"));
}

function checkNewData2() {
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

    var fn = document.getElementById("dataInputToolFile").value;
    var n = document.getElementById("dataInputToolName").value;
    // var isFacialData = document.getElementById("newFacialData").checked;
    var isNeuralData = document.getElementById("newNeuralData").checked;
    var isSpeechData = document.getElementById("newSpeechData").checked;
    // var isSiData = document.getElementById("newSiData").checked;
    var isLogData = document.getElementById("newLogData").checked;
    var isOtherData = document.getElementById("newOtherData").checked;

    var fn2 = fn.split(".");
    var fn3 = fn2[0].split("\\");

    console.log("isNeuralData: " + isNeuralData);
    console.log("isSpeechData: " + isSpeechData);
    console.log("isLogData: " + isLogData);
    console.log("isOtherData: " + isOtherData);

    if (fn == "") {
        alert("Please upload a file before continuing");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    if (n == "") {
        alert("Please enter a name for the new data before continuing");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    if (localStorage.getItem("newDataForStudyId") === "") {
        alert("Please select a study for the new data to be a part of before continuing");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    var selDataType = "";

    if(isNeuralData){
        selDataType = "Neural";
    } else if(isSpeechData){
        selDataType = "Speech";
    } else if(isLogData){
        selDataType = "Log Data";
    } else if(isOtherData){
        selDataType = "Other";
    }

    if (selDataType == "") {
        alert("Please select a data type before continuing");
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        return false;
    }

    // if(isFacialData){
    //     selDataType += "Facial";
    // }
    // if(isNeuralData){
    //     // if(selDataType != ""){
    //     //     selDataType += ",";
    //     // }
    //     selDataType = "Neural";
    // }
    // if(isSpeechData){
    //     // if(selDataType != ""){
    //     //     selDataType += ",";
    //     // }
    //     selDataType = "Speech";
    // }
    // // if(isSiData){
    // //     if(selDataType != ""){
    // //         selDataType += ",";
    // //     }
    // //     selDataType += "Stress Indicators";
    // // }
    // if(isLogData){
    //     // if(selDataType != ""){
    //     //     selDataType += ",";
    //     // }
    //     selDataType = "Log Data";
    // }

    console.log("In checkNewData selDataType: " + selDataType);

    var file = document.getElementById('dataInputToolFile').files[0];
    const reader = new FileReader();
    var fileAsText = reader.readAsText(file);
    console.log("in checkNewData fileAsText: " + fileAsText);
    // console.log("In checkNewData file: " + file.content);
    var fileBase64 = getBase64(file);
    console.log("In checkNewData fileBase64: " + fileBase64);

    var json = {file: localStorage.getItem("newDataContent"), filename: fn3[2], name: n, dataType: selDataType, ext: fn2[1], studyId: localStorage.getItem("newDataForStudyId"), authUserId: localStorage.getItem("currentAuthUserId")};

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
            processNewDataResponse2(xhr.responseText);
        } else {
            console.log("got an error");
            processNewDataResponse2("N/A");
        }
    };

    return false;
}

function processNewDataResponse2(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var data = js["data"];

    if (status == 200) {
        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        localStorage.setItem("newDataForStudyId", "");
        // window.location.href = "studyPage.html";
        window.location.href = localStorage.getItem("beforeUpload") + ".html";
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        alert(msg);
    }
}

function checkGetStudies3() {
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

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
            processGetStudiesResponse3(xhr.responseText);
        } else {
            console.log("got an error");
            processGetStudiesResponse3("N/A");
        }
    };

    return false;
}

function processGetStudiesResponse3(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var studies = js["studies"];

    if (status == 200) {
        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");

        console.log("getStudies3 status 200");
        console.log("studies.length: " + studies.length);

        if(studies.length === 0){
            localStorage.setItem("studiesExist", "false");
        } else {
            localStorage.setItem("studiesExist", "true");
        }
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        alert(msg);
    }
}

function checkGetDataDoc(docId) {
    //display loading gif
    document.getElementById("loadingGif").removeAttribute("hidden");

    var json = {documentId: docId};

    var js = JSON.stringify(json);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", GetDataDoc_url, true);

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
            processGetDataDocResponse(xhr.responseText);
        } else {
            console.log("got an error");
            processGetDataDocResponse("N/A");
        }
    };

    return false;
}

function processGetDataDocResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    var doc = js["doc"];

    if (status == 200) {
        //hide loading gif
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        console.log("getDataDoc status 200");

        var fileCSV = new File([doc.file], "userFile.csv", {
            type: "text/csv",
          });
        const obj_url = URL.createObjectURL(fileCSV);
        //set download href
        var docId = "download" + doc.documentId;
        document.getElementById(docId).setAttribute("href", obj_url);
    
        // const reader = new FileReader();
        // reader.readAsText(file);
        // reader.onload = function(evt) {
        //     console.log("in reader.onload");
        //     console.log(evt.target.result);
        //     console.log("reader.result: ");
        //     console.log(reader.result);
        //     content = reader.result.toString();
        //     console.log("in setContent content: "+ reader.result);
        //     console.log("in setContent content.toString(): " + content);
        // };
    } else {
        var msg = js["error"];
        console.log("error:" + msg);
        document.getElementById("loadingGif").setAttribute("hidden", "hidden");
        alert(msg);
    }
}