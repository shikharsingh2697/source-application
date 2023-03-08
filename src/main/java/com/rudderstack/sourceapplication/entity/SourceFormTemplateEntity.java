package com.rudderstack.sourceapplication.entity;

import com.rudderstack.sourceapplication.converters.HashMapConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SourceFormTemplates")
public class SourceFormTemplateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String type;
    @Convert(converter = HashMapConverter.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> fields;
    @UpdateTimestamp
    @Column
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;
}
