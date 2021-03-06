package info.tiamed.MoeWallpaper.data;

import java.util.List;

public class SourceData {
    /**
     * id : 9x6rbiVMSJg
     * created_at : 2018-12-24T17:48:47-05:00
     * updated_at : 2018-12-26T05:09:18-05:00
     * width : 3766
     * height : 5649
     * color : #FEC670
     * description : null
     * urls : {"raw":"https://images.unsplash.com/photo-1545691650-59c66cb1d3a0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjQ3Njg1fQ","full":"https://images.unsplash.com/photo-1545691650-59c66cb1d3a0?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjQ3Njg1fQ","regular":"https://images.unsplash.com/photo-1545691650-59c66cb1d3a0?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjQ3Njg1fQ","small":"https://images.unsplash.com/photo-1545691650-59c66cb1d3a0?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjQ3Njg1fQ","thumb":"https://images.unsplash.com/photo-1545691650-59c66cb1d3a0?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjQ3Njg1fQ"}
     * links : {"self":"https://api.unsplash.com/photos/9x6rbiVMSJg","html":"https://unsplash.com/photos/9x6rbiVMSJg","download":"https://unsplash.com/photos/9x6rbiVMSJg/download","download_location":"https://api.unsplash.com/photos/9x6rbiVMSJg/download"}
     * categories : []
     * sponsored : false
     * sponsored_by : null
     * sponsored_impressions_id : null
     * likes : 21
     * liked_by_user : false
     * current_user_collections : []
     * slug : null
     * user : {"id":"8XM-p13MRm4","updated_at":"2018-12-26T06:25:34-05:00","username":"ndvn","name":"Andre Iv","first_name":"Andre","last_name":"Iv","twitter_username":null,"portfolio_url":null,"bio":null,"location":null,"links":{"self":"https://api.unsplash.com/users/ndvn","html":"https://unsplash.com/@ndvn","photos":"https://api.unsplash.com/users/ndvn/photos","likes":"https://api.unsplash.com/users/ndvn/likes","portfolio":"https://api.unsplash.com/users/ndvn/portfolio","following":"https://api.unsplash.com/users/ndvn/following","followers":"https://api.unsplash.com/users/ndvn/followers"},"profile_image":{"small":"https://images.unsplash.com/profile-1544872413149-45e1b97f6b85?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32","medium":"https://images.unsplash.com/profile-1544872413149-45e1b97f6b85?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64","large":"https://images.unsplash.com/profile-1544872413149-45e1b97f6b85?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128"},"instagram_username":null,"total_collections":0,"total_likes":3,"total_photos":45,"accepted_tos":true}
     */

    private String id;
    private String created_at;
    private String updated_at;
    private int width;
    private int height;
    private String color;
    private Object description;
    private UrlsBean urls;
    private LinksBean links;
    private boolean sponsored;
    private Object sponsored_by;
    private Object sponsored_impressions_id;
    private int likes;
    private boolean liked_by_user;
    private Object slug;
    private UserBean user;
    private List<?> categories;
    private List<?> current_user_collections;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public UrlsBean getUrls() {
        return urls;
    }

    public void setUrls(UrlsBean urls) {
        this.urls = urls;
    }

    public LinksBean getLinks() {
        return links;
    }

    public void setLinks(LinksBean links) {
        this.links = links;
    }

    public boolean isSponsored() {
        return sponsored;
    }

    public void setSponsored(boolean sponsored) {
        this.sponsored = sponsored;
    }

    public Object getSponsored_by() {
        return sponsored_by;
    }

    public void setSponsored_by(Object sponsored_by) {
        this.sponsored_by = sponsored_by;
    }

    public Object getSponsored_impressions_id() {
        return sponsored_impressions_id;
    }

    public void setSponsored_impressions_id(Object sponsored_impressions_id) {
        this.sponsored_impressions_id = sponsored_impressions_id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLiked_by_user() {
        return liked_by_user;
    }

    public void setLiked_by_user(boolean liked_by_user) {
        this.liked_by_user = liked_by_user;
    }

    public Object getSlug() {
        return slug;
    }

    public void setSlug(Object slug) {
        this.slug = slug;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<?> getCategories() {
        return categories;
    }

    public void setCategories(List<?> categories) {
        this.categories = categories;
    }

    public List<?> getCurrent_user_collections() {
        return current_user_collections;
    }

    public void setCurrent_user_collections(List<?> current_user_collections) {
        this.current_user_collections = current_user_collections;
    }

    public static class UrlsBean {
        /**
         * raw : https://images.unsplash.com/photo-1545691650-59c66cb1d3a0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjQ3Njg1fQ
         * full : https://images.unsplash.com/photo-1545691650-59c66cb1d3a0?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjQ3Njg1fQ
         * regular : https://images.unsplash.com/photo-1545691650-59c66cb1d3a0?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjQ3Njg1fQ
         * small : https://images.unsplash.com/photo-1545691650-59c66cb1d3a0?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjQ3Njg1fQ
         * thumb : https://images.unsplash.com/photo-1545691650-59c66cb1d3a0?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjQ3Njg1fQ
         */

        private String raw;
        private String full;
        private String regular;
        private String small;
        private String thumb;

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public String getFull() {
            return full;
        }

        public void setFull(String full) {
            this.full = full;
        }

        public String getRegular() {
            return regular;
        }

        public void setRegular(String regular) {
            this.regular = regular;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }
    }

    public static class LinksBean {
        /**
         * self : https://api.unsplash.com/photos/9x6rbiVMSJg
         * html : https://unsplash.com/photos/9x6rbiVMSJg
         * download : https://unsplash.com/photos/9x6rbiVMSJg/download
         * download_location : https://api.unsplash.com/photos/9x6rbiVMSJg/download
         */

        private String self;
        private String html;
        private String download;
        private String download_location;

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public String getDownload() {
            return download;
        }

        public void setDownload(String download) {
            this.download = download;
        }

        public String getDownload_location() {
            return download_location;
        }

        public void setDownload_location(String download_location) {
            this.download_location = download_location;
        }
    }

    public static class UserBean {
        /**
         * id : 8XM-p13MRm4
         * updated_at : 2018-12-26T06:25:34-05:00
         * username : ndvn
         * name : Andre Iv
         * first_name : Andre
         * last_name : Iv
         * twitter_username : null
         * portfolio_url : null
         * bio : null
         * location : null
         * links : {"self":"https://api.unsplash.com/users/ndvn","html":"https://unsplash.com/@ndvn","photos":"https://api.unsplash.com/users/ndvn/photos","likes":"https://api.unsplash.com/users/ndvn/likes","portfolio":"https://api.unsplash.com/users/ndvn/portfolio","following":"https://api.unsplash.com/users/ndvn/following","followers":"https://api.unsplash.com/users/ndvn/followers"}
         * profile_image : {"small":"https://images.unsplash.com/profile-1544872413149-45e1b97f6b85?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32","medium":"https://images.unsplash.com/profile-1544872413149-45e1b97f6b85?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64","large":"https://images.unsplash.com/profile-1544872413149-45e1b97f6b85?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128"}
         * instagram_username : null
         * total_collections : 0
         * total_likes : 3
         * total_photos : 45
         * accepted_tos : true
         */

        private String id;
        private String updated_at;
        private String username;
        private String name;
        private String first_name;
        private String last_name;
        private Object twitter_username;
        private Object portfolio_url;
        private Object bio;
        private Object location;
        private LinksBeanX links;
        private ProfileImageBean profile_image;
        private Object instagram_username;
        private int total_collections;
        private int total_likes;
        private int total_photos;
        private boolean accepted_tos;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public Object getTwitter_username() {
            return twitter_username;
        }

        public void setTwitter_username(Object twitter_username) {
            this.twitter_username = twitter_username;
        }

        public Object getPortfolio_url() {
            return portfolio_url;
        }

        public void setPortfolio_url(Object portfolio_url) {
            this.portfolio_url = portfolio_url;
        }

        public Object getBio() {
            return bio;
        }

        public void setBio(Object bio) {
            this.bio = bio;
        }

        public Object getLocation() {
            return location;
        }

        public void setLocation(Object location) {
            this.location = location;
        }

        public LinksBeanX getLinks() {
            return links;
        }

        public void setLinks(LinksBeanX links) {
            this.links = links;
        }

        public ProfileImageBean getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(ProfileImageBean profile_image) {
            this.profile_image = profile_image;
        }

        public Object getInstagram_username() {
            return instagram_username;
        }

        public void setInstagram_username(Object instagram_username) {
            this.instagram_username = instagram_username;
        }

        public int getTotal_collections() {
            return total_collections;
        }

        public void setTotal_collections(int total_collections) {
            this.total_collections = total_collections;
        }

        public int getTotal_likes() {
            return total_likes;
        }

        public void setTotal_likes(int total_likes) {
            this.total_likes = total_likes;
        }

        public int getTotal_photos() {
            return total_photos;
        }

        public void setTotal_photos(int total_photos) {
            this.total_photos = total_photos;
        }

        public boolean isAccepted_tos() {
            return accepted_tos;
        }

        public void setAccepted_tos(boolean accepted_tos) {
            this.accepted_tos = accepted_tos;
        }

        public static class LinksBeanX {
            /**
             * self : https://api.unsplash.com/users/ndvn
             * html : https://unsplash.com/@ndvn
             * photos : https://api.unsplash.com/users/ndvn/photos
             * likes : https://api.unsplash.com/users/ndvn/likes
             * portfolio : https://api.unsplash.com/users/ndvn/portfolio
             * following : https://api.unsplash.com/users/ndvn/following
             * followers : https://api.unsplash.com/users/ndvn/followers
             */

            private String self;
            private String html;
            private String photos;
            private String likes;
            private String portfolio;
            private String following;
            private String followers;

            public String getSelf() {
                return self;
            }

            public void setSelf(String self) {
                this.self = self;
            }

            public String getHtml() {
                return html;
            }

            public void setHtml(String html) {
                this.html = html;
            }

            public String getPhotos() {
                return photos;
            }

            public void setPhotos(String photos) {
                this.photos = photos;
            }

            public String getLikes() {
                return likes;
            }

            public void setLikes(String likes) {
                this.likes = likes;
            }

            public String getPortfolio() {
                return portfolio;
            }

            public void setPortfolio(String portfolio) {
                this.portfolio = portfolio;
            }

            public String getFollowing() {
                return following;
            }

            public void setFollowing(String following) {
                this.following = following;
            }

            public String getFollowers() {
                return followers;
            }

            public void setFollowers(String followers) {
                this.followers = followers;
            }
        }

        public static class ProfileImageBean {
            /**
             * small : https://images.unsplash.com/profile-1544872413149-45e1b97f6b85?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32
             * medium : https://images.unsplash.com/profile-1544872413149-45e1b97f6b85?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64
             * large : https://images.unsplash.com/profile-1544872413149-45e1b97f6b85?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128
             */

            private String small;
            private String medium;
            private String large;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }
        }
    }
}
