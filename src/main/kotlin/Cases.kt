import java.net.http.HttpResponse

//GET LIST parameters
const val uriGetList = "https://reqres.in/api/users?page=2"
const val bodyRegexGetList = "\"page\":[0-9]+,\"per_page\":[0-9]+,\"total\":[0-9]+,\"total_pages\""
const val targetCodeGetList =  200

//GET SINGLE parameters
const val uriGetSingle = "https://reqres.in/api/users/2"
const val bodyRegexGetSingle = "\"id\":[0-9]+,\"email\":\".*\",\"first_name\":\"[A-z]+\",\"last_name\":\"[A-z]+\",\"avatar\":\".*jpg\""
const val targetCodeGetSingle =  200

//GET SINGLE (NOT FOUND) parameters
const val uriGetSingle404 = "https://reqres.in/api/users/23"
const val bodyRegexGetSingle404 = ""
const val targetCodeGetSingle404 =  404

//GET RESOURCE LIST parameters
const val uriGetResourceList = "https://reqres.in/api/unknown"
const val bodyRegexGetResourceList = "\"page\":[0-9]+,\"per_page\":[0-9]+,\"total\":[0-9]+,\"total_pages\""
const val targetCodeGetResourceList =  200

//GET SINGLE RESOURCE parameters
const val uriGetSingleResource = "https://reqres.in/api/unknown/3"
const val bodyRegexGetSingleResource = "\"id\":[0-9]+,\"name\":\"([A-z]||[ ])+\",\"year\":[0-9]+,\"color\":\"#.*[0-9]\",\"pantone_value\":\".*\""
const val targetCodeGetSingleResource =  200

//GET SINGLE RESOURCE (NOT FOUND) parameters
const val uriGetSingleResource404 = "https://reqres.in/api/unknown/300"
const val bodyRegexGetSingleResource404 = ""
const val targetCodeGetSingleResource404 =  404

//POST REGISTRATION SUCCESS
const val uriPostOk = "https://reqres.in/api/register"
const val bodyFilePostOk = "src/main/resources/RegisterSuccessBody.json"
const val bodyRegexPostOk = "\"id\":[0-9]+,\"token\":\".+\""
const val targetCodePostOk =  200

const val headerKeyPostOk1 = "Content-Type"
const val headerValuePostOk1 = "application/json"

//POST REGISTRATION FAIL
const val uriPostFail = "https://reqres.in/api/register"
const val bodyFilePostFail = "src/main/resources/RegisterFailBody.json"
const val bodyRegexPostFail = "\"error\":.*\""
const val targetCodePostFail =  400

const val headerKeyPostFail1 = "Content-Type"
const val headerValuePostFail1 = "application/json"

//PUT
const val uriPut = "https://reqres.in/api/users/2"
const val bodyFilePut = "src/main/resources/PutBody.json"
const val bodyRegexPut = "\"updatedAt\":\".+\""
const val targetCodePut =  200

const val headerKeyPut1 = "Content-Type"
const val headerValuePut1 = "application/json"

//PATCH parameters
const val uriPatch = "https://reqres.in/api/users/3"
const val bodyFilePatch = "src/main/resources/PatchBody.json"
const val bodyRegexPatch = "\"updatedAt\":\".+\""
const val targetCodePatch =  200

const val headerKeyPatch1 = "Content-Type"
const val headerValuePatch1 = "application/json"

//DELETE parameters
const val uriDelete = "https://reqres.in/api/users/2"
const val bodyRegexDelete = ""
const val targetCodeDelete =  204

class Cases() {
    companion object {

        // Function to print results of response assertions (code and body) in console
        private fun printCheckResult(
            responseToPrint: HttpResponse<String>,
            codeCheckResult: Boolean,
            bodyCheckResult: Boolean
        ) {
            println("\nCODE: " + responseToPrint.statusCode())
            println("BODY: " + responseToPrint.body())
            println("\nCODE CHECK: $codeCheckResult, BODY CHECK: $bodyCheckResult")
            println("---+---")
        }

        //GET LIST function
        fun getList() {
            val responseOfGetList = Sender.sendGet(uriGetList)
            val listDoubleCheck = (Checker.verifyBody(responseOfGetList, bodyRegexGetList)
                    && Checker.verifyBody(responseOfGetList, bodyRegexGetSingle))

            printCheckResult(
                responseOfGetList,
                Checker.verifyCode(responseOfGetList, targetCodeGetList),
                listDoubleCheck
            )
        }

        //GET SINGLE function
        fun getSingle() {
            val responseOfGetSingle = Sender.sendGet(uriGetSingle)
            printCheckResult(
                responseOfGetSingle, Checker.verifyCode(responseOfGetSingle, targetCodeGetSingle),
                Checker.verifyBody(responseOfGetSingle, bodyRegexGetSingle)
            )
        }

        //GET SINGLE (NOT FOUND) function
        fun getSingle404() {
            val responseOfGet404 = Sender.sendGet(uriGetSingle404)
            printCheckResult(
                responseOfGet404,
                Checker.verifyCode(responseOfGet404, targetCodeGetSingle404),
                Checker.verifyBody(responseOfGet404, bodyRegexGetSingle404)
            )
        }

        //GET RESOURCE LIST function
        fun getResourceList() {
            val responseOfGetResourceList = Sender.sendGet(uriGetResourceList)
            val listDoubleCheck = (Checker.verifyBody(responseOfGetResourceList, bodyRegexGetResourceList)
                    && Checker.verifyBody(responseOfGetResourceList, bodyRegexGetSingleResource))

            printCheckResult(
                responseOfGetResourceList,
                Checker.verifyCode(responseOfGetResourceList, targetCodeGetResourceList),
                listDoubleCheck
            )
        }

        //GET SINGLE RESOURCE function
        fun getSingleResource() {
            val responseOfGetSingleResource = Sender.sendGet(uriGetSingleResource)
            printCheckResult(
                responseOfGetSingleResource,
                Checker.verifyCode(responseOfGetSingleResource, targetCodeGetSingleResource),
                Checker.verifyBody(responseOfGetSingleResource, bodyRegexGetSingleResource)
            )
        }

        //GET SINGLE RESOURCE (NOT FOUND) function
        fun getSingleResource404() {
            val responseOfGetSingleResource404 = Sender.sendGet(uriGetSingleResource404)
            printCheckResult(
                responseOfGetSingleResource404,
                Checker.verifyCode(responseOfGetSingleResource404, targetCodeGetSingleResource404),
                Checker.verifyBody(responseOfGetSingleResource404, bodyRegexGetSingleResource404)
            )
        }

        //POST REGISTER (SUCCESS) function
        fun postOk() {
            val responseOfPostOk = Sender.sendPost(uriPostOk, bodyFilePostOk, headerKeyPostOk1, headerValuePostOk1)
            printCheckResult(
                responseOfPostOk, Checker.verifyCode(responseOfPostOk, targetCodePostOk),
                Checker.verifyBody(responseOfPostOk, bodyRegexPostOk)
            )
        }

        //POST REGISTER (FAIL) function
        fun postFail() {
            val responseOfPostFail =
                Sender.sendPost(uriPostFail, bodyFilePostFail, headerKeyPostFail1, headerValuePostFail1)
            printCheckResult(
                responseOfPostFail, Checker.verifyCode(responseOfPostFail, targetCodePostFail),
                Checker.verifyBody(responseOfPostFail, bodyRegexPostFail)
            )
        }

        //PUT function
        fun put() {
            val responseOfPut = Sender.sendPut(uriPut, bodyFilePut, headerKeyPut1, headerValuePut1)
            printCheckResult(
                responseOfPut, Checker.verifyCode(responseOfPut, targetCodePut),
                Checker.verifyBody(responseOfPut, bodyRegexPut)
            )
        }

        //DELETE function
        fun delete() {
            val responseOfDelete = Sender.sendDelete(uriDelete)
            printCheckResult(
                responseOfDelete, Checker.verifyCode(responseOfDelete, targetCodeDelete),
                Checker.verifyBody(responseOfDelete, bodyRegexDelete)
            )
        }

        //PUT function
        fun patch() {
            val responseOfPatch = Sender.sendPatch(uriPatch, bodyFilePatch, headerKeyPatch1, headerValuePatch1)
            printCheckResult(
                responseOfPatch, Checker.verifyCode(responseOfPatch, targetCodePatch),
                Checker.verifyBody(responseOfPatch, bodyRegexPatch)
            )
        }
    }
}
