package info.team23h.acc.entity.bbs;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import info.team23h.acc.entity.BaseTimeEntity;
import info.team23h.acc.vo.front.gallery.GalleryUpdateVO;
import info.team23h.acc.vo.front.video.VideoUpdateVO;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.hateoas.Link;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "TB_BBS")
public class Bbs extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ")
	private Long seq;

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name="NAME_SEQ")
	@JsonManagedReference
	private TbBbsName tbBbsName;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "REG_ID")
	private String regId;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name="MAIN_FILE_NAME")
	private String mainFileName;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="BBS_SEQ")
	@JsonManagedReference
	private List<BbsComment> bbsCommentList;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "SEQ")
	@JsonManagedReference
	private List<BbsFile> bbsFileList;

	@Transient
	private Link _link;

	public void set_link(Link _link) {
		this._link = _link;
	}

	@Builder
	public Bbs(TbBbsName tbBbsName, String title, String content, String regId, String password, String mainFileName, List<BbsFile> bbsFileList) {
		this.tbBbsName = tbBbsName;
		this.title = title;
		this.content = content;
		this.regId = regId;
		this.password = password;
		this.mainFileName = mainFileName;
		this.bbsFileList = bbsFileList;
	}

	/**
	 * 겔러리 수정
	 *
	 * @param galleryUpdateVO the gallery update vo
	 * @param fileList        the file list
	 */
	public void update(GalleryUpdateVO galleryUpdateVO, List<BbsFile> fileList) {
		if(!ObjectUtils.isEmpty(galleryUpdateVO.getTitle())){
			this.title = galleryUpdateVO.getTitle();
		}
		if(!ObjectUtils.isEmpty(galleryUpdateVO.getMainFIleName())){
			this.mainFileName = galleryUpdateVO.getMainFIleName();
		}
		if(fileList.size() > 0){
			fileList.forEach(bbsFile -> {
				bbsFileList.add(bbsFile);
			});
		}
	}

	public void updateVideo(VideoUpdateVO videoUpdateVO) {
		this.content = videoUpdateVO.getVideoUrl();
		this.title = videoUpdateVO.getTitle();
	}
}
