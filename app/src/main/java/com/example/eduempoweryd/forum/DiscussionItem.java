////package com.example.forum;
////
////
////import java.util.List;
////
////public class DiscussionItem {
////        private String key;
////        private String topic;
////        private String content;
////        private List<Comment> comments;
////
////        public DiscussionItem() {
////                // Default constructor required for calls to DataSnapshot.getValue(DiscussionItem.class)
////        }
////
////        public DiscussionItem(String key, String topic, String content, List<Comment> comments) {
////                this.key = key;
////                this.topic = topic;
////                this.content = content;
////                this.comments = comments;
////        }
////
////        public String getKey() {
////                return key;
////        }
////
////        public String getTopic() {
////                return topic;
////        }
////
////        public void setTopic(String topic) {
////                this.topic = topic;
////        }
////
////        public String getContent() {
////                return content;
////        }
////
////        public void setContent(String content) {
////                this.content = content;
////        }
////
////        public List<Comment> getComments() {
////                return comments;
////        }
////
////        public void setComments(List<Comment> comments) {
////                this.comments = comments;
////        }
////}
//package com.example.forum;
//
//public class DiscussionItem {
//        private String key;
//        private String topic;
//        private String comment;
//
//        public DiscussionItem() {
//        }
//
//        public DiscussionItem(String key, String topic, String comment) {
//                this.key = key;
//                this.topic = topic;
//                this.comment = comment;
//        }
//
//        public String getTopic() {
//                return topic;
//        }
//
//        public void setTopic(String topic) {
//                this.topic = topic;
//        }
//
//        public String getComment() {
//                return comment;
//        }
//
//        public void setComment(String comment) {
//                this.comment = comment;
//        }
//}
package com.example.eduempoweryd.forum;

public class DiscussionItem {
        private String key;
        private String topic;
        private String comment;

        public DiscussionItem() {
        }

        public DiscussionItem(String key, String topic, String comment) {
                this.key = key;
                this.topic = topic;
                this.comment = comment;
        }

        public String getTopic() {
                return topic;
        }

        public void setTopic(String topic) {
                this.topic = topic;
        }

        public String getComment() {
                return comment;
        }

        public void setComment(String comment) {
                this.comment = comment;
        }

        public String getKey() {return key;}
}
