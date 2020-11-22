package info.team23h.acc.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseTimeEntity {

	@Column(name = "REG_DT")
	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime regDt;

	@Column(name = "UPD_DT")
	@UpdateTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime updDt;
}

