package com.fecakarate.backendfecakarate.Services.mapper;

import com.fecakarate.backendfecakarate.Dtos.organization.MemberDto;
import com.fecakarate.backendfecakarate.Models.Member;
import org.mapstruct.Mapper;

@Mapper
public interface MemberMapper {

    MemberDto toDto(Member member);

    Member toEntity(MemberDto memberDto);
}

