package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/pets")
@Produces("application/json")
public class PetResource {
	List<Pet> pets = new ArrayList<Pet>();
	List<PetType> petTypes = new ArrayList<>();
	//Pet Id initializing
	int petId = 1;
	//PetType Id initializing
	int petTypeId=1;

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets() {
		return Response.ok(pets).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		else {
			for(int i=0; i<pets.size(); i++){
				if (petId == pets.get(i).getPetId()){
					return Response.ok(pets.get(i)).build();
				}
			}

		}

		return Response.status(Status.NOT_FOUND).build();
	}
	@GET
	@Path("/type/all")
	public Response getAllPetTypes(){
		return Response.ok(petTypes).build();
	}

	@POST
	@Path("/add")
	public Response addPet(@RequestBody Pet pet){
		pet.setPetId(petId);
		petId++;
		pets.add(pet);
		return Response.ok(pet).build();
	}

	@POST
	@Path("/type/add")
	public Response addCategory (@RequestBody PetType type ){
		type.setTypeId(petTypeId);
		petTypeId++;
		petTypes.add(type);
		return Response.ok(petTypes).build();
	}

	@DELETE
	@Path("/delete/{petId}")
	public  Response deletePet(@PathParam("petId") int petId){
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		else{
			for (int i=0; i<pets.size(); i++){
				if (petId == pets.get(i).getPetId()){
					pets.remove(i);
					return Response.ok(pets).build();
				}
			}
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("/type/delete/{typeId}")
	public Response deleteType(@PathParam("typeId") int typeId ){
		if (typeId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		else{
			for (int i=0; i<petTypes.size(); i++){
				if (typeId == petTypes.get(i).getTypeId()){
					String type = petTypes.get(i).getPetType();
					petTypes.remove(i);
					for (int j=0; j< pets.size(); j++){
						if(type.equals(pets.get(j).getPetType())){
							pets.remove(j);
							j=j-1;
						}
					}
					return Response.ok(petTypes).build();

				}
			}
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@PUT
	@Path("/update/{petId}")
	public Response updatePet(@PathParam("petId") int petId,@RequestBody Pet pet){
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		else{
			for (int i=0; i<pets.size(); i++){
				if (petId == pets.get(i).getPetId()){
					if (pet.getPetAge()!=null){
						pets.get(i).setPetAge(pet.getPetAge());
					}
					if (pet.getPetName()!=null){
						pets.get(i).setPetName(pet.getPetName());
					}
					if (pet.getPetType()!=null){
						pets.get(i).setPetType(pet.getPetType());
					}
					return Response.ok(pets.get(i)).build();

				}
			}
			return Response.status(Status.NOT_FOUND).build();
		}

	}

	@PUT
	@Path("type/update/{typeId}")
	public Response updateType(@PathParam("typeId") int typeId, @RequestBody PetType petType){
		if (typeId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		else{
			for (int i=0; i<petTypes.size(); i++){
				if (typeId == petTypes.get(i).getTypeId()){
					String type = petTypes.get(i).getPetType();
					petTypes.get(i).setPetType(petType.getPetType());
					for (int j=0; j< pets.size(); j++){
						if(type.equals(pets.get(j).getPetType())){
							pets.get(j).setPetType(petType.getPetType());
						}
					}
					return Response.ok(petTypes).build();

				}
			}
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@POST
	@Path("/search")
	public Response petSearch(@RequestBody Search search){
		List<Pet> searchList = new ArrayList<>();
		if (search.getName()!= null){
			System.out.println("Name++++++++++++");
			for (int i =0; i<pets.size(); i++){
				if (pets.get(i).getPetName().contains(search.getName())){
					searchList.add(pets.get(i));
				}
			}
		}
		if (searchList.size()==0){
			return Response.status(Status.NOT_FOUND).build();
		}
		else {
			return Response.ok(searchList).build();
		}
	}








}
