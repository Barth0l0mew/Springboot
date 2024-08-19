package org.itstep;

import org.itstep.model.ModelCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {
        @Autowired
        private ModelRepository modelRepository;

        public ModelCar findById(Long id) {
                return modelRepository.findById(id).orElse(null);
        }
} 