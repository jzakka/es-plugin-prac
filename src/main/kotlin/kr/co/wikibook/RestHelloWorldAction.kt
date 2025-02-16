package kr.co.wikibook

import org.elasticsearch.action.ActionListener
import org.elasticsearch.client.internal.node.NodeClient
import org.elasticsearch.rest.BaseRestHandler
import org.elasticsearch.rest.RestHandler
import org.elasticsearch.rest.RestHandler.Route
import org.elasticsearch.rest.RestRequest
import java.lang.Exception

class RestHelloWorldAction : BaseRestHandler() {
    override fun routes(): MutableList<RestHandler.Route> = mutableListOf(Route(RestRequest.Method.GET, "/_hello"))

    override fun getName(): String = "hello_world_action"

    override fun prepareRequest(request: RestRequest, client: NodeClient): RestChannelConsumer {
        val name = request.param("name", "world")
        val actionRequest = HelloWorldRequest(name)

        return RestChannelConsumer { channel -> client.execute(HelloWorldAction, actionRequest,
            object : ActionListener<HelloWorldResponse> {
                override fun onResponse(response: HelloWorldResponse) {
                    channel.sendResponse(DefaultRestResponse.success(response, channel))
                }

                override fun onFailure(e: Exception) {
                    channel.sendResponse(DefaultRestResponse.error(e))
                }
            }
        ) }
    }

}