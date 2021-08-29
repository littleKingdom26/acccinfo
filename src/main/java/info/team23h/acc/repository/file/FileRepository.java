package info.team23h.acc.repository.file;

import info.team23h.acc.entity.bbs.BbsFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<BbsFile,Long> {}
