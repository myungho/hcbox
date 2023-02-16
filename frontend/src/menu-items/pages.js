// assets
import { LoginOutlined, SkinOutlined, HomeOutlined, FileAddOutlined } from '@ant-design/icons';

// icons
const icons = {
    LoginOutlined,
    SkinOutlined,
    HomeOutlined,
    FileAddOutlined
};

// ==============================|| MENU ITEMS - EXTRA PAGES ||============================== //

const pages = {
    id: 'Management',
    title: 'Management',
    type: 'group',
    children: [
        {
            id: 'order-info',
            title: 'OrderInfo',
            type: 'item',
            url: '/order-info',
            icon: icons.FileAddOutlined,
            breadcrumbs: true
        },
        {
            id: 'order-list',
            title: 'OrderInfoList',
            type: 'item',
            url: '/order-list',
            icon: icons.FileAddOutlined,
            breadcrumbs: true
        },
        {
            id: 'products',
            title: 'Product',
            type: 'item',
            url: '/products',
            icon: icons.SkinOutlined,
            breadcrumbs: true
        },
        {
            id: 'schools',
            title: 'Schools',
            type: 'item',
            url: '/schools',
            icon: icons.HomeOutlined,
            breadcrumbs: true
        }
    ]
};

export default pages;
