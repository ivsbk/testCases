import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class Sender() {
    companion object {

        //Send GET request with given URI(String)
        fun sendGet(uri: String): HttpResponse<String> {

            val client = HttpClient.newBuilder().build();
            val request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());

            println(request.method() + " request to " + uri)

            return response
        }

        //Send POST request with given URI(String), path(String) to body file and headers (Key:String, Value:String for each header)
        fun sendPost(uri: String, bodyPath: String, headerKey1: String, headerValue1: String): HttpResponse<String> {

            val requestBody = File(bodyPath).readText(Charsets.UTF_8)

            val client = HttpClient.newBuilder().build();
            val request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .headers(headerKey1, headerValue1)
                .build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());

            println(request.method() + " request to " + uri)
            println("With body:\n$requestBody")

            return response
        }

        //Send PUT request with given URI(String), path(String) to body file and headers (Key:String, Value:String for each header)
        fun sendPut(uri: String, bodyPath: String, headerKey1: String, headerValue1: String): HttpResponse<String> {

            val requestBody = File(bodyPath).readText(Charsets.UTF_8)

            val client = HttpClient.newBuilder().build();
            val request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .headers(headerKey1, headerValue1)
                .build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());

            println(request.method() + " request to " + uri)
            println("With body:\n$requestBody")

            return response
        }

        //Send DELETE request with given URI(String)
        fun sendDelete(uri: String): HttpResponse<String> {

            val client = HttpClient.newBuilder().build();
            val request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(uri))
                .build();
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());

            println(request.method() + " request to " + uri)

            return response
        }

        //Send PATCH request with given URI(String), path(String) to body file and headers (Key:String, Value:String for each header)
        fun sendPatch(uri: String, bodyPath: String, headerKey1: String, headerValue1: String): HttpResponse<String> {

            val requestBody = File(bodyPath).readText(Charsets.UTF_8)

            val client = HttpClient.newBuilder().build();
            val request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody))
                .headers(headerKey1, headerValue1)
                .build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());

            println(request.method() + " request to " + uri)
            println("With body:\n$requestBody")

            return response
        }

    }
}
