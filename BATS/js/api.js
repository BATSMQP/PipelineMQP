// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point
var base_url = "https://es7rigcmle.execute-api.us-east-2.amazonaws.com/beta/";

var CreateStudy_url = base_url + "CreateStudy"; //POST
var GetData_url = base_url + "GetData"; //POST
var getStudies_url = base_url + "getStudies"; //POST
var Login_url = base_url + "Login"; //POST
var newData_url = base_url + "newData"; //POST
var newTool_url = base_url + "newTool"; //POST
var Register_url = base_url + "Register"; //POST
var saveAnalysis_url = base_url + "saveAnalysis"; //POST
