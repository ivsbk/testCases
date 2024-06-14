//import java.io.File
import java.net.http.HttpResponse

class Checker {
    companion object {

        //Compare response code from server with target response code value (Int)
        fun verifyCode(responseChecked: HttpResponse<String>, targetCodeChecked: Int): Boolean {
            return responseChecked.statusCode() == targetCodeChecked
        }

        //Compare response body from server with given response schema (String)
        fun verifyBody(responseChecked: HttpResponse<String>, bodyRegexChecked: String): Boolean {
            val regex = bodyRegexChecked.toRegex()
            return regex.containsMatchIn(responseChecked.body())
        }

        }
    }
