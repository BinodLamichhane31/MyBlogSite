package com.example.myblogsite.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
   String resourceName;
   String fieldName;
   long fiendValue;

   public ResourceNotFoundException(String resourceName, String fieldName, long fiendValue) {
       super(String.format("%s not found with %s: %s", resourceName,fieldName,fiendValue));
       this.resourceName = resourceName;
       this.fieldName = fieldName;
       this.fiendValue = fiendValue;
   }
}
