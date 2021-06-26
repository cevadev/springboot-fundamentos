package com.fundamentosplatzi.springboot.fundamentos.repository;

import com.fundamentosplatzi.springboot.fundamentos.dto.UserDto;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @Query("Select u From User u Where u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("Select u From User u Where u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    // definimos un Query method
    List<User> findByName(String name);

    // definimos un query method
    Optional<User> findByNameAndEmail(String name, String email);

    // query method: obtener user a partir del nombre (Like)
    List<User> findByNameLike(String name);

    // query method: obtener user por nomnre o email
    List<User> findByNameOrEmail(String name, String email);

    // query method: obtener user a partir de un intevalo de fecha
    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    // query method: obtenemos los users ordenamos de manera descendente por el nombre
    List<User> findByNameLikeOrderByNameDesc(String name);

    // query method: Obtenemos los users que contenta una cadena
    List<User> findByNameContainingOrderByIdDesc(String name);

    // Trabajando con JPQL con Named Parameters
    @Query("Select new com.fundamentosplatzi.springboot.fundamentos.dto.UserDto(u.id, u.name, u.birthDate)" +
            " From User u" +
            " Where u.birthDate=:parameterFecha" +
            " And u.email=:parameterEmail")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("parameterFecha") LocalDate date,
                                                @Param("parameterEmail") String email);

    List<User> findAll();
}
