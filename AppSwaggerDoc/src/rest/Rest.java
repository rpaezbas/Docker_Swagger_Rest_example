package rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import io.swagger.v3.oas.annotations.responses.ApiResponse;



@Path("/cars")
public class Rest {

	Response response;

	// Get every car in table Car
	@GET
	@Path("/")
	@ApiResponse(responseCode = "200", description = "Everything ok.")
    @ApiResponse(responseCode = "404", description = "No cars found.")
	@ApiResponse(responseCode = "403", description = "Invalid token.")
	@Produces("application/json")
	public Response getAllCars(@HeaderParam("authorization") final String authorization) {
		return response;

	}

	// Get single car by carId
	@GET
	@Path("/{carId}")
	@ApiResponse(responseCode = "200", description = "Everything ok.")
    @ApiResponse(responseCode = "404", description = "No car found.")
	@ApiResponse(responseCode = "403", description = "Invalid token.")
	@Produces("application/json")
	public Response getCarbyId(@PathParam("carId") final int carId,
			@HeaderParam("authorization") final String authorization) {
		return response;
	}

	// Create car
	@POST
	@Path("/")
	@ApiResponse(responseCode = "200", description = "Everything ok.")
    @ApiResponse(responseCode = "405", description = "The request is not well-formed.")
	@ApiResponse(responseCode = "403", description = "Invalid token.")
	@Consumes("application/json")
	@Produces("application/json")
	public Response postCar(final Car car, @HeaderParam("authorization") final String authorization) {		
		return response;
	}

	// Update car by carId
	@PUT
	@Path("/{carId}")
	@ApiResponse(responseCode = "201", description = "Everything ok.")
    @ApiResponse(responseCode = "404", description = "The car to be updated does not exist.")
	@ApiResponse(responseCode = "403", description = "Invalid token.")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateCar(@PathParam("carId") final int carId, final Car updatedCar,
			@HeaderParam("authorization") final String authorization) {
		return response;
	}

	// Delete single car by carId
	@DELETE
	@Path("/{carId}")
	@ApiResponse(responseCode = "200", description = "Everything ok.")
    @ApiResponse(responseCode = "404", description = "The car to be deleted does not exist.")
	@ApiResponse(responseCode = "403", description = "Invalid token.")
	@Produces("application/json")
	public Response deleteCarbyId(@PathParam("carId") int carId,
			@HeaderParam("authorization") final String authorization) {
		return response;
	}
}
