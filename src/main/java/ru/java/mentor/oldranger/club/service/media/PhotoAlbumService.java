package ru.java.mentor.oldranger.club.service.media;

import ru.java.mentor.oldranger.club.dto.PhotoAlbumDto;
import ru.java.mentor.oldranger.club.dto.PhotoWithAlbumDTO;
import ru.java.mentor.oldranger.club.model.media.Photo;
import ru.java.mentor.oldranger.club.model.media.PhotoAlbum;
import ru.java.mentor.oldranger.club.model.user.User;

import java.util.List;

public interface PhotoAlbumService {

    PhotoAlbum save(PhotoAlbum album);

    List<PhotoAlbum> findAll();

    List<PhotoWithAlbumDTO> getAllPhotoWithAlbumsDTO(PhotoAlbum album);

    List<Photo> getAllPhotos(PhotoAlbum album);

    void deleteAllAlbums();

    void deleteAlbum(Long id);

    PhotoAlbum findById(Long id);

    PhotoAlbum update(PhotoAlbum album);

    void deleteAlbumPhotos(boolean deleteAll, PhotoAlbum album);

    List<PhotoAlbum> findPhotoAlbumsViewedByUser(User user);

    List<PhotoAlbumDto> findPhotoAlbumsDtoOwnedByUser(User user);

    PhotoAlbum findPhotoAlbumByTitle(String name);

    void createAlbum(PhotoAlbum photoAlbum);

    PhotoAlbumDto assemblePhotoAlbumDto(PhotoAlbum album);
}
