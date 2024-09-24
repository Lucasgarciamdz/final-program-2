package ar.edu.um.pcbuilder.dtos.responses;

public record BaseResponse<T>(String menssage, T object) {

}
