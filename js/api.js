// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point
var base_url = 'https://es7rigcmle.execute-api.us-east-2.amazonaws.com/beta/';

var CreateStudy_url = base_url + 'CreateStudy'; //POST
var GetData_url = base_url + 'GetData'; //POST
var GetStudyData_url = base_url + 'GetStudyData'; //POST
var GetStudyTools_url = base_url + 'GetStudyTools'; //POST
var getStudies_url = base_url + 'getStudies'; //POST
var Login_url = base_url + 'Login'; //POST
var NewData_url = base_url + 'NewData'; //POST
var NewTool_url = base_url + 'NewTool'; //POST
var Register_url = base_url + 'Register'; //POST
var saveAnalysis_url = base_url + 'saveAnalysis'; //POST
var GetUsername_url = base_url + 'GetUsername'; //POST
var RunAlg_url = base_url + 'RunAlg'; //POST
