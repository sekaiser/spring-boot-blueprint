package de.seka.news.modules.articles.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="article")
@Builder
@Getter
@Setter
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String identifier;
    @Column
    private String header;
    @Column(columnDefinition="TEXT")
    private String description;
    @Column(columnDefinition="TEXT")
    private String text;

    @CreatedDate
    @Builder.Default
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime createdDate = LocalDateTime.now(ZoneOffset.UTC);

    // assumption: keywords are separated by spaces
    @Column
    private String keywords;

    // assumption author onsist of firstname lastname; authors are seprated by ','
    @Column
    private String authors;
}