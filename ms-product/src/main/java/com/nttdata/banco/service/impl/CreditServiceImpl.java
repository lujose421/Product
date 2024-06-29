package com.nttdata.banco.service.impl;

import com.nttdata.banco.Mapper.CreditMapper;
import com.nttdata.banco.persistence.entity.Credit;
import com.nttdata.banco.persistence.repository.CreditRepository;
import com.nttdata.banco.service.CreditService;
import com.nttdata.product.openapi.model.CreditDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditService {
    @Autowired
    private CreditRepository creditRepository;

    private static final Logger logger = LoggerFactory.getLogger(BankAccountServiceImpl.class);

    @Override
    public Flux<CreditDTO> getAllCredits() {
        logger.info("service getAllCredit -ini");
        Flux<CreditDTO> creditsFlux = this.creditRepository.findAll()
                .doOnError(error -> logger.error("Error getAllCredits", error))
                .map(CreditMapper::entityToDto);
        creditsFlux.count()
                .doOnNext(count -> logger.info("Count credits: {}", count))
                .subscribe();
        return creditsFlux
                .doOnTerminate(() -> logger.info("service geAllCredit - end"));
    }

    @Override
    public Mono<CreditDTO> getCreditById(String creditId) {
        logger.info("service getBankAccountById - ini");
        return this.creditRepository.findById(creditId)
                .doOnError(error -> logger.error("Error getCreditById: ", error))
                .map(CreditMapper::entityToDto)
                .doOnTerminate(() -> logger.info("service getBCreditById - end"));
    }

    @Override
    public Mono<CreditDTO> createCredit(CreditDTO creditDTO) {
        logger.info("service createCredit - ini");
        return Mono.just(creditDTO)
                .map(CreditMapper::dtoToEntity)
                .flatMap(this.creditRepository::insert)
                .map(CreditMapper::entityToDto)
                .doOnNext(credit -> logger.info("credit create succesfull"))
                .doOnTerminate(() -> logger.info("service credit - end"));
    }


    @Override
    public Mono<CreditDTO> updateCredit(String creditId, CreditDTO creditDTO) {
        logger.info("service updateCredit - ini");
        return this.creditRepository.findById(creditId)
                .doOnError(error -> logger.info("Error updateCredit: ", error))
                .flatMap(existCredit -> {
                    Credit creditUpdate = CreditMapper.dtoToEntity(creditDTO);
                    creditUpdate.setId(existCredit.getId());
                    logger.info("credit by id: {}, body credit: {}", existCredit.getId());
                    return this.creditRepository.save(creditUpdate);
                })
                .map(CreditMapper::entityToDto)
                .doOnTerminate(() -> logger.info("service updateCredit - end"));

    }

    @Override
    public Mono<Void> deleteCredit(String id) {
        return creditRepository.deleteById(id);
    }
}
