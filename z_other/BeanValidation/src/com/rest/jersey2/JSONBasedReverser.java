package com.rest.jersey2;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/jsonbasedreverser")
public class JSONBasedReverser {

	@Path("{word}")
	@GET
	@Produces("application/json")
	//@Valid
	public Response reverser(
			@PathParam("word") 
			@NotNull(message = "Not null") 
			@Pattern(regexp = "[0-9]+", message = "Message - The id must be a valid number") String word)
			throws JSONException {

		StringBuilder sb = new StringBuilder();
		sb.append(word);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("original1", sb.toString());
		jsonObject.put("reversed1", sb.reverse().toString());

		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}

}