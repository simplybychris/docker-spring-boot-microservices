package com.springapp.familyapp.service;

import com.springapp.familyapp.assembler.FamilyAssembler;
import com.springapp.familyapp.dao.FamilyRepository;
import com.springapp.familyapp.model.Family;
import com.springapp.familyapp.model.dto.FamilyDto;
import com.springapp.familyapp.model.dto.FamilyMemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class FamilyService {

    private final WebClient webClient;
    @Autowired
    private FamilyRepository familyRepository;

    public FamilyService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://localhost:8090/api")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Transactional
    public Long create(FamilyDto familyDto) {
        var result = FamilyAssembler.create(familyDto);
        validateFamilyData(familyDto);
        result = familyRepository.save(result);

        for (FamilyMemberDto familyMemberDto : familyDto.getFamilyMembers()) {
            familyMemberDto.setFamilyId(result.getId());
            if (ObjectUtils.isEmpty(familyMemberDto.getFamilyName())) {
                familyMemberDto.setFamilyName(result.getFamilyName());
            }
        }
        createFamilyMembers(familyDto.getFamilyMembers());

        return result.getId();
    }

    private List<FamilyMemberDto> getFamilyMembers(Long familyId) {
        return webClient.get()
                .uri("/family-members/" + familyId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<FamilyMemberDto>>() {
                })
                .block();
    }

    private void createFamilyMembers(List<FamilyMemberDto> familyMemberDtos) {
        webClient.post()
                .uri("/family-members")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(familyMemberDtos))
                .retrieve().bodyToMono(FamilyMemberDto.class).block();
    }

    private void validateFamilyData(FamilyDto familyDto) {
        if (CollectionUtils.isEmpty(familyDto.getFamilyMembers())) {
            throw new RuntimeException("Missing data about family members.");
        }

        int sumOfMembers = familyDto.getNrOfAdults() + familyDto.getNrOfChildren() + familyDto.getNrOfInfants();
        if (sumOfMembers != familyDto.getFamilyMembers().size()) {
            throw new RuntimeException("Incorrect number of family members list.");
        }

        if (!isFamilyMemberNumberValid(familyDto)) {
            throw new RuntimeException("Incorrect number of family members.");
        }
    }

    private boolean isFamilyMemberNumberValid(FamilyDto familyDto) {
        int infantsNo = 0;
        int childrenNo = 0;
        int adultsNo = 0;
        for (FamilyMemberDto familyMember : familyDto.getFamilyMembers()) {
            int age = familyMember.getAge();
            if (age < 4 && age > 0) {
                infantsNo++;
            } else if (age < 16 && age > 4) {
                childrenNo++;
            } else {
                adultsNo++;
            }
        }

        return familyDto.getNrOfInfants() == infantsNo
                && familyDto.getNrOfChildren() == childrenNo
                && familyDto.getNrOfAdults() == adultsNo;
    }

    @Transactional
    public FamilyDto getFamily(Long id) {
        Family familyMember = familyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Family with provided number not found."));

        List<FamilyMemberDto> familyMemberDtos = getFamilyMembers(familyMember.getId());

        FamilyDto familyDto = FamilyAssembler.map(familyMember);
        familyDto.setFamilyMembers(familyMemberDtos);
        return familyDto;
    }
}
