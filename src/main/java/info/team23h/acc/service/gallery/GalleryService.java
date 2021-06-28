package info.team23h.acc.service.gallery;

import info.team23h.acc.vo.gallery.GalleryResultVO;
import info.team23h.acc.vo.gallery.GallerySaveVO;

import java.io.IOException;

public interface GalleryService {

	GalleryResultVO save(GallerySaveVO gallerySaveVO) throws IOException;
}
