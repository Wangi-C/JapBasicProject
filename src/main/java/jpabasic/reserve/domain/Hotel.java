package jpabasic.reserve.domain;

import jakarta.persistence.*;
import jpabasic.common.Address;

import java.time.LocalDateTime;

/**
 * <엔티티 클래스 제약조건>
 * @Entity 적용
 * @Id 적용
 * 기본 생성자 필요
 * 기본 생성자는 public 이나 protected 여야함
 * 최상위 클래스여야 함
 * final이면 안됌
 *
 * <접근 방법>
 * 필드 접근 : 필드 값을 사용해서 매핑
 * 프로퍼티 접근 : gettr, setter 메소드를 사용해서 매핑
 *
 * @Id 를 필드에 붙이면 필드 접근
 *     를 getter메소드에 붙이면 프로퍼티 접근
 * @Access 사용해서 명시적으로 지정
 * @Access(AccessType.PROPERTY) / @Access(AccessType.FIELD)
 *
 * <식별 컬럼 방식>
 * @Id 가 붙은 필드에 추가적으로
 * @GeneratedValue(strategy = GenerateType.IDENTITY) 설정
 *
 * persist() 실행할 때 객체에 식별자 값 할당 -> commit 시점
 * **/

@Entity
@Table(name = "hotel_info")
public class Hotel {
    @Id
    @Column(name = "hotel_id")
    private String id;

    @Column(name = "nm")
    private String name;

    private int year;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Embedded
    private Address address;

    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime lastModified;

    protected Hotel() {
    }

    public Hotel(String id, String name, int year, Grade grade, Address address) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.grade = grade;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public Grade getGrade() {
        return grade;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", grade=" + grade +
                ", created=" + created +
                ", lastModified=" + lastModified +
                '}';
    }

    public Address getAddress() {
        return address;
    }
    /**
     * @Entity : 엔티티 클래스에 설정, 필수
     * @Table : 매핑한 테이블 지정
     *         name -> 테이블명
     *         catalog -> MySQL db 명
     *         schema -> 스키마 이름
     *    default -> class명과 같은 table
     * @Id : 식별자 속성에 설정, 필수
     * @Column : 매핑할 컬럼명 지정
     *    지정하지 않으면 필드명, 프로퍼팀ㅇ 사용
     * @Enumerated : enum 타입 매핑할 때 설정
     * **/
}
