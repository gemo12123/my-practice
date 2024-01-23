package org.mytest.test.mapper;

import lombok.Data;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FishConverter {

    FishConverter INSTANCE = Mappers.getMapper(FishConverter.class);

    @Data
    public static class FishTank {
        String material;
        int length;
        int width;
        int height;
    }

    @Data
    public static class FishTankWithVolumeDto {
        VolumeDto volume;
    }

    @Data
    public static class VolumeDto {
        int volume;
        String description;

        public VolumeDto(int volume, String description) {
            this.volume = volume;
            this.description = description;
        }
    }


    /**
     * source需要指定为自定义方法的参数名
     *
     * @param source
     * @return
     */
    @Mapping(target = "volume", source = "fish")
    FishTankWithVolumeDto map(FishTank fish);

    default VolumeDto mapVolume(FishTank source) {
        int volume = source.length * source.width * source.height;
        String desc = volume < 100 ? "Small" : "Large";
        return new VolumeDto(volume, desc);
    }

}
