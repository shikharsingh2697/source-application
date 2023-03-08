package com.rudderstack.sourceapplication.entity;

import com.rudderstack.sourceapplication.converters.HashMapConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Sources")
@Accessors(chain = true)
public class SourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sourceFormTemplateId")
    private SourceFormTemplateEntity sourceFormTemplateEntity;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;
    @Convert(converter = HashMapConverter.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> data;
    @UpdateTimestamp
    @Column
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;
}