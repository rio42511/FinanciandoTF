package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

	@Query("select count(b.phoneBank) from Bank b where b.phoneBank =:phone")
    public int buscarBanco(@Param("phone") String telefonoBanco);

    @Query("select b from Bank b where b.phoneBank like %:phone%")
    List<Bank> findByphone(String phone);

    @Query("select b from Bank b where b.phoneBank like %:phone%")
    List<Bank> findByphoneLikeIgnoreCase(String phone);

}
