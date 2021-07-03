package info.team23h.acc.service.gallery;

import info.team23h.acc.vo.front.gallery.GalleryResultVO;
import info.team23h.acc.vo.front.gallery.GallerySaveVO;

import java.io.IOException;

public interface GalleryService {

	GalleryResultVO save(GallerySaveVO gallerySaveVO) throws IOException;
}
